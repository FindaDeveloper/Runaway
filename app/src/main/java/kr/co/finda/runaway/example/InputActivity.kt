package kr.co.finda.runaway.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kr.co.finda.runaway.example.data.GeneratedNameContainer
import kr.co.finda.runaway.example.data.NameContainer

class InputActivity : AppCompatActivity() {

    private val nameContainer: NameContainer by lazy {
        GeneratedNameContainer.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val etName = findViewById<EditText>(R.id.et_name)

        findViewById<Button>(R.id.btn_complete).setOnClickListener {
            nameContainer.name = etName.text.toString()
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }
}