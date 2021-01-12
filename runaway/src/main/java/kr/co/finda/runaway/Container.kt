package kr.co.finda.runaway

import kotlin.reflect.KClass

annotation class Container(
    val scopes: Array<KClass<*>>
)