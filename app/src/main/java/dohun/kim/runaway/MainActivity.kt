package dohun.kim.runaway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.annotation.Container

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val javaTest = GeneratedJavaTest(this)
        val loanApplyContainer = GeneratedLoanApplyContainer(this)
    }
}

@Container(
    scopes = [
        MainActivity::class
    ]
)
interface LoanApplyContainer {
    val hello: String
}

