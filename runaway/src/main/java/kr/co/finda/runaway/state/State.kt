package kr.co.finda.runaway.state

import com.squareup.kotlinpoet.TypeName

data class State(
    val typeName: TypeName,
    val name: String
)