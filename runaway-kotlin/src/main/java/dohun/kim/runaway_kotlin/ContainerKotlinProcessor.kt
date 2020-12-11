package dohun.kim.runaway_kotlin

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import dohun.kim.runaway_kotlin.state.State
import dohun.kim.runaway_kotlin.state.generateState
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.MirroredTypesException
import javax.lang.model.type.TypeMirror

@AutoService(Processor::class)
class ContainerKotlinProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Container::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(
        set: MutableSet<out TypeElement>,
        roundEnvironment: RoundEnvironment
    ): Boolean {
        val containerElements = roundEnvironment.getElementsAnnotatedWith(Container::class.java)

        containerElements.forEach { containerElement ->
            val container = containerElement.getAnnotation(Container::class.java)
            val scopeTypes = getScopesFromAnnotation(container)
            val fileName = "Generated" + containerElement.simpleName.toString()
            val packageName = processingEnv.elementUtils.getPackageOf(containerElement).toString()
            val fileSpecBuilder = FileSpec.builder(packageName, fileName)
            val containerElementTypeName = containerElement.asType().asTypeName()

            val states = getStatesFromContainer(containerElement)
            generateContainer(
                fileSpecBuilder,
                containerElementTypeName,
                fileName,
                states,
                scopeTypes,
                packageName
            )
            generateGetOrDefaults(fileSpecBuilder, containerElementTypeName, states)
//            generateFromBundle(fileSpecBuilder, containerElementTypeName, states)
//            generateToBundle(fileSpecBuilder, containerElementTypeName, states)

            fileSpecBuilder.build().writeTo(processingEnv.filer)
        }
        return true
    }

    private fun getStatesFromContainer(containerElement: Element): List<State> {
        val states = arrayListOf<State>()
        containerElement.enclosedElements.forEach { stateElement ->
            try {
                val state = generateState(stateElement, states)
                states.add(state)
            } catch (e: Exception) {
            }
        }
        return states
    }

    private fun generateContainer(
        fileSpecBuilder: FileSpec.Builder,
        containerElementTypeName: TypeName,
        fileName: String,
        states: List<State>,
        scopes: List<TypeMirror>,
        packageName: String,
    ) {
        val containerBuilder = TypeSpec.classBuilder(fileName)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addModifiers(KModifier.PRIVATE)
                    .build()
            )
        containerBuilder.addSuperinterface(containerElementTypeName)

        containerBuilder.addProperties(
            states.map {
                PropertySpec.builder(it.name, it.typeName.copy(true))
                    .addModifiers(KModifier.OVERRIDE)
                    .initializer("null")
                    .mutable(true)
                    .build()
            }
        )

        addCompanionObject(containerBuilder, scopes, packageName, fileName)

        fileSpecBuilder.addType(containerBuilder.build())
    }

    private fun addCompanionObject(
        containerBuilder: TypeSpec.Builder,
        scopes: List<TypeMirror>,
        packageName: String,
        fileName: String
    ) {
        val containerClass = ClassName(packageName, fileName)
        val companionBuilder = TypeSpec.companionObjectBuilder()
            .addProperty(
                PropertySpec.builder("INSTANCE", containerClass.copy(true))
                    .addModifiers(KModifier.PRIVATE)
                    .initializer("null")
                    .mutable(true)
                    .build()
            )
        scopes.forEach { typeMirror ->
            companionBuilder.addFunction(
                FunSpec.builder("getInstance")
                    .returns(containerClass)
                    .addParameter("scope", typeMirror.asTypeName())
                    .addStatement("return INSTANCE ?: ${containerClass.simpleName}().also { INSTANCE = it }")
                    .build()
            )
        }

        containerBuilder.addType(companionBuilder.build())
    }

    private fun generateGetOrDefaults(
        fileSpecBuilder: FileSpec.Builder,
        containerElementTypeName: TypeName,
        states: List<State>
    ) {
        states.forEach { state ->
            fileSpecBuilder.addFunction(
                FunSpec.builder("get${state.name.capitalize(Locale.getDefault())}OrDefault")
                    .receiver(containerElementTypeName)
                    .returns(state.typeName)
                    .addParameter("default", state.typeName)
                    .beginControlFlow("if (${state.name} == null)")
                    .addStatement("${state.name} = default")
                    .endControlFlow()
                    .addStatement("return ${state.name}!!")
                    .build()
            )
        }
    }

    private fun generateFromBundle(
        fileSpecBuilder: FileSpec.Builder,
        containerElementTypeName: TypeName,
        states: List<State>
    ) {
        val bundleClassName = ClassName("android.os", "Bundle")
        val fromBundleSpec = FunSpec.builder("fromBundle")
            .receiver(containerElementTypeName)
            .addParameter("bundle", bundleClassName)

        states.forEach { state ->
            fromBundleSpec.addStatement("${state.name} = bundle[\"${state.name}\"] as? ${state.typeName}")
        }

        fileSpecBuilder.addFunction(fromBundleSpec.build())
    }

    private fun generateToBundle(
        fileSpecBuilder: FileSpec.Builder,
        containerElementTypeName: TypeName,
        states: List<State>
    ) {
        val bundleClassName = ClassName("android.os", "Bundle")
        val toBundleSpec = FunSpec.builder("toBundle")
            .receiver(containerElementTypeName)
            .returns(bundleClassName)

        toBundleSpec.addStatement("val bundle = ${bundleClassName.canonicalName}()")
        states.forEach { state ->
            toBundleSpec.addStatement(
                "${state.name}?.let { bundle.put${state.typeName.toBundleTypeName()}(\"${state.name}\", it) }"
            )
        }
        toBundleSpec.addStatement("return bundle")

        fileSpecBuilder.addFunction(toBundleSpec.build())
    }

    /**
     * [Container] 어노테이션에서 Class[] 정보를 얻기 위해서 아래 방식을 사용합니다.
     *
     * @link https://area-51.blog/2009/02/13/getting-class-values-from-annotations-in-an-annotationprocessor/
     */
    private fun getScopesFromAnnotation(container: Container): List<TypeMirror> {
        try {
            container.scopes // must throw MirroredTypesException
        } catch (mte: MirroredTypesException) {
            return mte.typeMirrors
        }
        throw IllegalStateException() // Can't reach
    }
}