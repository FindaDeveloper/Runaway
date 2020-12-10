package dohun.kim.runaway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val calculatorContainer: CalculatorContainer by lazy {
        GeneratedCalculatorContainer.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstValue = calculatorContainer.firstValue ?: 0
        val secondValue = calculatorContainer.secondValue ?: 0

        binding.tvResult.text = (firstValue + secondValue).toString()
    }
}