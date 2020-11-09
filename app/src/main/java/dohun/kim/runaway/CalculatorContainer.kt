package dohun.kim.runaway

import dohun.kim.runaway.annotation.Container

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
}