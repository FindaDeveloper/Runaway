package dohun.kim.runaway_kotlin

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import java.lang.IllegalStateException
import javax.lang.model.element.ExecutableElement
import javax.lang.model.type.TypeMirror
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap
import kotlin.reflect.jvm.internal.impl.name.FqName

fun ExecutableElement.getTypeName(): TypeName {
    return javaToKotlinType(this.returnType) ?: throw IllegalStateException()
}

private fun javaToKotlinType(typeMirror: TypeMirror): TypeName? {
    val className = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(FqName(typeMirror.asTypeName().toString()))?.asSingleFqName()?.asString()
    return if (className == null) {
        typeMirror.asTypeName()
    } else {
        ClassName.bestGuess(className)
    }
}

fun TypeName.toBundleTypeName(): String {
    return when(this.toString()) {
        "kotlin.Int" -> "Int"
        "kotlin.Double" -> "Double"
        "kotlin.String" -> "String"
        "kotlin.Byte" -> "Byte"
        "kotlin.Char" -> "Char"
        "kotlin.Short" -> "Short"
        "kotlin.Float" -> "Float"
        else -> "Serializable"
    }
}