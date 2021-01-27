package kr.co.finda.runaway.data

import kr.co.finda.runaway.InputActivity
import kr.co.finda.runaway.ResultActivity
import kr.co.finda.runaway.annotation.Container

@Container(
    scopes = [
        InputActivity::class,
        ResultActivity::class
    ]
)
interface NameContainer {

    var name: String?
}