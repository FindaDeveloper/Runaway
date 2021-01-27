package kr.co.finda.runaway.processor

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import javax.lang.model.element.ExecutableElement
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap
import kotlin.reflect.jvm.internal.impl.name.FqName

fun ExecutableElement.getTypeName(): TypeName {
    return this.returnType.asTypeName().javaToKotlinType()
}

private fun TypeName.javaToKotlinType(): TypeName = if (this is ParameterizedTypeName) {
    (rawType.javaToKotlinType() as ClassName).parameterizedBy(
        *typeArguments.map { it.javaToKotlinType() }.toTypedArray()
    )
} else {
    val className = JavaToKotlinClassMap.INSTANCE
        .mapJavaToKotlin(FqName(toString()))?.asSingleFqName()?.asString()
    if (className == null) this
    else ClassName.bestGuess(className)
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