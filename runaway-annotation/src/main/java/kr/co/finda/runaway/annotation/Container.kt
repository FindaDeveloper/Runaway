package kr.co.finda.runaway.annotation

import kotlin.reflect.KClass

annotation class Container(
    val scopes: Array<KClass<*>>
)