package dohun.kim.runaway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.runaway.databinding.ActivitySecondValueBinding

class SecondValueActivity : BaseActivity() {

    private lateinit var binding: ActivitySecondValueBinding

    override val container: GeneratedCalculatorContainer by lazy {
        GeneratedCalculatorContainer.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondValueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val enteredValue = binding.etSecondValue.text.toString().toInt()
            container.secondValue = enteredValue
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }
}