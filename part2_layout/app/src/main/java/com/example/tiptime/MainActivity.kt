package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}