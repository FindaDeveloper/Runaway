package kr.co.finda.runaway.example.data

import kr.co.finda.runaway.example.InputActivity
import kr.co.finda.runaway.annotation.Container
import kr.co.finda.runaway.example.ResultActivity

@Container(
    scopes = [
        InputActivity::class,
        ResultActivity::class
    ]
)
interface NameContainer {

    var name: String?
}