package dohun.kim.runaway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val calculatorContainer: GeneratedCalculatorContainer by lazy {
        GeneratedCalculatorContainer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstValue = calculatorContainer.firstValue
        val secondValue = calculatorContainer.secondValue

        binding.tvResult.text = (firstValue + secondValue).toString()
    }
}