package dohun.kim.runaway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivityFirstValueBinding

class FirstValueActivity : BaseActivity() {

    private lateinit var binding: ActivityFirstValueBinding

    override val container: CalculatorContainer by lazy {
        GeneratedCalculatorContainer.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstValueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val enteredValue = binding.etFirstValue.text.toString().toInt()
            container.firstValue = enteredValue
            startActivity(Intent(this, SecondValueActivity::class.java))
        }
    }
}