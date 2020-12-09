package dohun.kim.runaway

import dohun.kim.runaway.annotation.Container
import java.io.Serializable

@Container(
    scopes = [
        FirstValueActivity::class,
        SecondValueActivity::class,
        ResultActivity::class
    ]
)
interface CalculatorContainer {

    val firstValue: Int

    val secondValue: Int

    val string: String

    val a: A
}

data class A(val a: String) : Serializable