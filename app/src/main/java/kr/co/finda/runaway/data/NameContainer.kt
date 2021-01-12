package kr.co.finda.runaway.data

import kr.co.finda.runaway.Container
import kr.co.finda.runaway.InputActivity
import kr.co.finda.runaway.ResultActivity

@Container(
    scopes = [
        InputActivity::class,
        ResultActivity::class
    ]
)
interface NameContainer {

    var name: String?
}