package dohun.kim.runaway_kotlin

import kotlin.reflect.KClass

annotation class Container(
    val scopes: Array<KClass<*>>
)