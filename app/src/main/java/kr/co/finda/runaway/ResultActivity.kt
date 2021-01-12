package kr.co.finda.runaway

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kr.co.finda.runaway.data.GeneratedNameContainer
import kr.co.finda.runaway.data.NameContainer

class ResultActivity : AppCompatActivity() {

    private val nameContainer: NameContainer by lazy {
        GeneratedNameContainer.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        tvResult.text = nameContainer.name
    }
}