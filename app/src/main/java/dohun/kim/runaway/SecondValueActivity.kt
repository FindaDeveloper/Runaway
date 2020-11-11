package dohun.kim.runaway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivitySecondValueBinding

class SecondValueActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondValueBinding

    private val calculatorContainer: CalculatorContainer by lazy {
        GeneratedCalculatorContainer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondValueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val enteredValue = binding.etSecondValue.text.toString().toInt()
            calculatorContainer.secondValue = enteredValue
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }
}