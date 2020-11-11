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

    var firstValue: Int

    var secondValue: Int

    var string: String
}