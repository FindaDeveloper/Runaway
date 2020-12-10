package dohun.kim.runaway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivityResultBinding

class ResultActivity : BaseActivity() {

    private lateinit var binding: ActivityResultBinding

    override val container: CalculatorContainer by lazy {
        GeneratedCalculatorContainer.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstValue = container.getFirstValueOrDefault(0)
        val secondValue = container.getSecondValueOrDefault(0)

        binding.tvResult.text = (firstValue + secondValue).toString()
    }
}
