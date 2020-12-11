package dohun.kim.runaway

import dohun.kim.runaway_kotlin.Container
import java.io.Serializable

@Container(
    scopes = [
        FirstValueActivity::class,
        SecondValueActivity::class,
        ResultActivity::class
    ]
)
interface CalculatorContainer {

    var firstValue: Int?

    var secondValue: Int?

    var string: String?

    var a: A?

    var list: List<String>?
}

data class A(val a: String) : Serializable