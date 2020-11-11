package dohun.kim.runaway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivityFirstValueBinding

class FirstValueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstValueBinding

    private val calculatorContainer: GeneratedCalculatorContainer by lazy {
        GeneratedCalculatorContainer(this)
    }

    private val javaTest: GeneratedJavaTest by lazy {
        GeneratedJavaTest(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstValueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val enteredValue = binding.etFirstValue.text.toString().toInt()
            calculatorContainer.firstValue = enteredValue
            startActivity(Intent(this, SecondValueActivity::class.java))
        }
    }
}