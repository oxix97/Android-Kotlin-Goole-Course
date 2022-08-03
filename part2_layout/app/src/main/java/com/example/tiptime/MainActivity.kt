package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMainCalculate.setOnClickListener {
            calculateTip()
        }
        binding.etMainCostText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip() {
        val cost = binding.etMainCostText.text.toString().toInt()
        val selectTips = binding.rgMainContainer.checkedRadioButtonId
        val tipPersent = when (selectTips) {
            R.id.rb_main_amazing -> 0.2
            R.id.rb_main_good -> 0.18
            R.id.rb_main_ok -> 0.15
            else -> 0.0
        }
        var tip = tipPersent * cost
        val isRoundUp = binding.swMainSwitch.isChecked

        if (isRoundUp) tip = kotlin.math.ceil(tip)
        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvMainTipResult.text = formatTip
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}