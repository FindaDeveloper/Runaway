package dohun.kim.runaway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dohun.kim.runaway.databinding.ActivityFirstValueBinding

abstract class BaseActivity : AppCompatActivity() {

    abstract val container: CalculatorContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.getBundle("calculator")?.let {
            container.fromBundle(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle("calculator", container.toBundle())
    }
}