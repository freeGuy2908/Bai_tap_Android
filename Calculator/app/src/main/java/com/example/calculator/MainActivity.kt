package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textResult: TextView

    var calculation = ""
    var calculation1 = ""
    var calculation2 = ""
    var state: Int = 1
    var state1: Int = 0
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0
    var cal: Int = 0
    var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        findViewById<TextView>(R.id.btnCE).setOnClickListener(this)
        findViewById<TextView>(R.id.btnC).setOnClickListener(this)
        findViewById<TextView>(R.id.btnBack).setOnClickListener(this)
        findViewById<TextView>(R.id.btn0).setOnClickListener(this)
        findViewById<TextView>(R.id.btn1).setOnClickListener(this)
        findViewById<TextView>(R.id.btn2).setOnClickListener(this)
        findViewById<TextView>(R.id.btn3).setOnClickListener(this)
        findViewById<TextView>(R.id.btn4).setOnClickListener(this)
        findViewById<TextView>(R.id.btn5).setOnClickListener(this)
        findViewById<TextView>(R.id.btn6).setOnClickListener(this)
        findViewById<TextView>(R.id.btn7).setOnClickListener(this)
        findViewById<TextView>(R.id.btn8).setOnClickListener(this)
        findViewById<TextView>(R.id.btn9).setOnClickListener(this)
        findViewById<TextView>(R.id.btnAdd).setOnClickListener(this)
        findViewById<TextView>(R.id.btnSub).setOnClickListener(this)
        findViewById<TextView>(R.id.btnMul).setOnClickListener(this)
        findViewById<TextView>(R.id.btnDiv).setOnClickListener(this)
        findViewById<TextView>(R.id.btnDot).setOnClickListener(this)
        findViewById<TextView>(R.id.btnEqual).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            val id = it.id
            when (id) {
                R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8,
                R.id.btn9 -> {
                    state1 = 0
                    calculation += (it as TextView).text
                    calculation1 += it.text
                    result += it.text
                    textResult.text = calculation
                }

                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv -> {
                    cal = when (id) {
                        R.id.btnAdd -> 1
                        R.id.btnSub -> 2
                        R.id.btnMul -> 3
                        R.id.btnDiv -> 4
                        else -> 0
                    }

                    if (state == 1) {
                        if (calculation == "" && state1 != 1) {
                            return
                        } else if (state1 == 1) {
                            calculation = textResult.text.toString() + (it as TextView).text
                            textResult.text = calculation
                            return
                        } else {
                            state = 2
                            op1 = calculation1.toInt()
                        }
                    }

                    calculation1 = ""
                    calculation += (it as TextView).text
                    calculation2 = calculation
                    textResult.text = calculation
                }

                R.id.btnEqual -> {
                    if (calculation == "+" || calculation == "-" || calculation == "*" || calculation == "/") {
                        return
                    }
                    if (state == 1) {
                        textResult.text = textResult.text.toString()
                    }
                    if (calculation == "" && state == 1) {
                        textResult.text == calculation
                        return
                    }
                    if (textResult.text.toString() == "" && state == 1) {
                        textResult.text = calculation
                        return
                    }
                    if (state == 2 && calculation1 == "") {
                        return
                    }

                    op2 = calculation1.toInt()
                    when (cal) {
                        1 -> {
                            textResult.text = (op1 + op2).toString()
                            op1 += op2
                        }
                        2 -> {
                            textResult.text = (op1 - op2).toString()
                            op1 -= op2
                        }
                        3 -> {
                            textResult.text = (op1 * op2).toString()
                            op1 *= op2
                        }
                        4 -> {
                            if (op2 == 0) {
                                textResult.text = "Syntax error"
                                return
                            }
                            textResult.text = (op1.toDouble() / op2).toString()
                            op1 /= op2
                        }
                    }
                    state = 1
                    state1 = 1
                    calculation = ""
                    calculation1 = ""
                }

                R.id.btnC -> {
                    if (state == 1) {
                        calculation = ""
                        calculation1 = ""
                        textResult.text = calculation
                    }
                    if (state == 2) {
                        calculation = calculation2
                        calculation1 = ""
                        textResult.text = calculation
                    }
                }

                R.id.btnCE -> {
                    calculation = ""
                    calculation1 = ""
                    calculation2 = ""
                    state1 = 0
                    state = 1
                    op1 = 0
                    textResult.text = calculation
                }

                R.id.btnBack -> {
                    if (state == 1) {
                        if (calculation == "") {
                            textResult.text = calculation
                            return
                        } else if (textResult.text.toString() == ""){
                            textResult.text = calculation
                            return
                        } else {
                            calculation = calculation.substring(0, calculation.length - 1)
                            calculation1 = calculation1.substring(0, calculation1.length - 1)
                            textResult.text = calculation
                        }
                    }
                    else if (state == 2) {
                        if (calculation1 == "") {
                            state = 1
                            calculation = calculation.substring(0, calculation.length - 1)
                            calculation1 = calculation
                            textResult.text = calculation
                        } else {
                            calculation = calculation.substring(0, calculation.length - 1)
                            calculation1 = calculation1.substring(0, calculation1.length - 1)
                            textResult.text = calculation
                        }
                    }
                }
            }
        }
    }
}