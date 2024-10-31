package com.example.week8ex1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.etNumber)
//        val rbEven: RadioButton = findViewById(R.id.rbEven)
//        val rbOdd: RadioButton = findViewById(R.id.rbOdd)
//        val rbSquare: RadioButton = findViewById(R.id.rbSquare)
        val rbGroup: RadioGroup = findViewById(R.id.radioGroup)
        val btnShow: Button = findViewById(R.id.btnShow)
        val lvResult: ListView = findViewById(R.id.lvResult)
        val tvError: TextView = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isEmpty()) {
                tvError.text = "Vui long khong de trong!"
                return@setOnClickListener
            }
            val n = editText.text.toString().toIntOrNull()
            if (n == null || n < 0) {
                tvError.text = "So nguyen n khong hop le!"
                return@setOnClickListener
            }
            tvError.text = ""
            val selectedId = rbGroup.checkedRadioButtonId
            val results = mutableListOf<Int>()
            when (selectedId) {
                R.id.rbEven -> results.addAll(genEvenNums(n))
                R.id.rbOdd -> results.addAll(genOddNums(n))
                R.id.rbSquare -> results.addAll(genSquareNums(n))
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            lvResult.adapter = adapter
        }
    }

    private fun genSquareNums(n: Int): List<Int> {
        val cps = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            cps.add(i * i)
            i++
        }
        return cps
    }

    private fun genOddNums(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun genEvenNums(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

}