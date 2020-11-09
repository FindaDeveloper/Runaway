package dohun.kim.runaway;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import dohun.kim.runaway.annotation.Container;
import dohun.kim.runaway.field.FieldGenerator;
import dohun.kim.runaway.field.FieldGeneratorFactory;
import dohun.kim.runaway.state.State;
import dohun.kim.runaway.state.StateGenerator;
import dohun.kim.runaway.state.StateGeneratorFactory;
import dohun.kim.runaway.util.StringUtil;

@AutoService(Processor.class)
public class ContainerProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(Container.class.getCanonicalName());
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> containerElements = roundEnvironment.getElementsAnnotatedWith(Container.class);

        for (Element containerElement : containerElements) {
            Container container = containerElement.getAnnotation(Container.class);
            List<? extends TypeMirror> scopeTypes = getScopesFromAnnotation(container);
            String fileName = "Generated" + containerElement.getSimpleName().toString();
            String packageName = processingEnv.getElementUtils().getPackageOf(containerElement).toString();

            List<State> states = getStatesFromContainer(containerElement);

            TypeSpec containerSpec = generateContainer(fileName, states, scopeTypes);
            writeContainer(containerSpec, packageName);
        }
        return true;
    }

    private List<State> getStatesFromContainer(Element containerElement) {
        List<? extends Element> stateElements = containerElement.getEnclosedElements();
        List<State> states = new ArrayList<>();

        for (Element stateElement : stateElements) {
            if (stateElement.getKind() == ElementKind.CONSTRUCTOR) {
                continue;
            }

            StateGenerator stateGenerator = StateGeneratorFactory.getStateGenerator(stateElement);
            State state = stateGenerator.generateState();
            states.add(state);
        }

        return states;
    }

    private TypeSpec generateContainer(
            String fileName,
            List<State> states,
            List<? extends TypeMirror> scopeTypes
    ) {
        TypeSpec.Builder containerBuilder = TypeSpec.classBuilder(fileName);
        generateScopeConstructors(containerBuilder, scopeTypes);
        generateFields(containerBuilder, states);

        return containerBuilder.build();
    }

    private void generateScopeConstructors(
            TypeSpec.Builder containerBuilder,
            List<? extends TypeMirror> scopeTypes
    ) {
        MethodSpec privateDefaultConstructorSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .build();

        containerBuilder.addMethod(privateDefaultConstructorSpec);

        for (TypeMirror scopeType : scopeTypes) {
            MethodSpec scopeConstructorSpec = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(TypeName.get(scopeType), StringUtil.getSimpleNameFromClassPackage(scopeType.toString()))
                    .build();

            containerBuilder.addMethod(scopeConstructorSpec);
        }
    }

    private void generateFields(TypeSpec.Builder containerBuilder, List<State> states) {
        for (State state : states) {
            FieldGenerator fieldGenerator = FieldGeneratorFactory.getFieldGenerator(state);

            containerBuilder.addField(fieldGenerator.generateFieldSpec());
            containerBuilder.addMethod(fieldGenerator.generateGetterMethodSpec());
            containerBuilder.addMethod(fieldGenerator.generateSetterMethodSpec());
            containerBuilder.addMethod(fieldGenerator.generateGetOrDefaultMethodSpec());
        }
    }

    private void writeContainer(TypeSpec containerSpec, String packageName) {
        try {
            JavaFile.builder(packageName, containerSpec)
                    .build()
                    .writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            Messager messager = processingEnv.getMessager();
            String message = "Unable to write file: " + e.getMessage();
            messager.printMessage(Diagnostic.Kind.ERROR, message);
        }
    }

    private List<? extends TypeMirror> getScopesFromAnnotation(Container container) {
        try {
            container.scopes();
        } catch (MirroredTypesException mte) {
            return mte.getTypeMirrors();
        }
        return null;
    }
}

