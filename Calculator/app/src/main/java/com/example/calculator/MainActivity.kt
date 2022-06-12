package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test01.R

class MainActivity : AppCompatActivity() {


    private lateinit var btn_1: Button
    private lateinit var btn_2: Button
    private lateinit var btn_3: Button
    private lateinit var btn_4: Button
    private lateinit var btn_5: Button
    private lateinit var btn_6: Button
    private lateinit var btn_7: Button
    private lateinit var btn_8: Button
    private lateinit var btn_9: Button
    private lateinit var btn_0: Button
    private lateinit var btn_plus: Button
    private lateinit var btn_minus: Button
    private lateinit var btn_multiply: Button
    private lateinit var btn_division: Button
    private lateinit var btn_equls: Button
    private lateinit var btn_dot: Button
    private lateinit var btn_sing: Button
    private lateinit var btn_back: Button
    private lateinit var btn_c: Button
    private lateinit var btn_ce: Button

    private lateinit var txt_result: TextView
    private lateinit var txt_history: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindViews()
        btn_c.setOnClickListener {
            txt_result.text = "0"
            txt_history.text = ""
            mostReset = false
            currentResult = 0f
        }

        btn_ce.setOnClickListener {
            txt_result.text = "0"

        }

        val numberListener = View.OnClickListener {
            val index:Int = it.tag.toString().toInt()
            appendNumber(index)
        }


        btn_0.setOnClickListener(numberListener)
        btn_1.setOnClickListener(numberListener)
        btn_2.setOnClickListener(numberListener)
        btn_3.setOnClickListener(numberListener)
        btn_4.setOnClickListener(numberListener)
        btn_5.setOnClickListener(numberListener)
        btn_6.setOnClickListener(numberListener)
        btn_7.setOnClickListener(numberListener)
        btn_8.setOnClickListener(numberListener)
        btn_9.setOnClickListener(numberListener)

        val opperandListener = View.OnClickListener {
            if (mostReset){
                txt_result.text = "0"
                mostReset = false

            }

            doOperand(it.tag.toString())

        }

        btn_plus.setOnClickListener(opperandListener)
        btn_minus.setOnClickListener(opperandListener)
        btn_multiply.setOnClickListener(opperandListener)
        btn_division.setOnClickListener(opperandListener)

        btn_equls.setOnClickListener{
            processEqual("")
        }

        btn_back.setOnClickListener{
            backOneLatter()
        }

        btn_dot.setOnClickListener{
            dot()

        }

        btn_sing.setOnClickListener {
            singed()
        }

    }

    private fun bindViews() {

        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        btn_0 = findViewById(R.id.btn_0)

        btn_plus = findViewById(R.id.btn_plus)
        btn_minus = findViewById(R.id.btn_minus)
        btn_multiply = findViewById(R.id.btn_multiply)
        btn_division = findViewById(R.id.btn_division)

        btn_equls = findViewById(R.id.btn_equal)
        btn_dot = findViewById(R.id.btn_dot)
        btn_sing = findViewById(R.id.btn_sing)
        btn_back = findViewById(R.id.btn_back)

        btn_c = findViewById(R.id.btn_c)
        btn_ce = findViewById(R.id.btn_ce)

        txt_result = findViewById(R.id.txt_result)
        txt_history = findViewById(R.id.txt_history)
    }

    var mostReset = false
    var currentResult = 0f
    var operand = ""
    @SuppressLint("SetTextI18n")
    private fun compute(newOperand: String) {
        val result = (txt_result.text).toString().toFloat()


        when (operand) {
            "+" -> {
                currentResult += result
            }
            "-" -> {
                currentResult -= result
            }
            "*" -> {
                currentResult *= result
            }
            "/" -> {
                currentResult /= result
            }
            else -> {
                currentResult = result
            }
        }

        val oldHistory = txt_history.text.toString()
        txt_history.text = "$oldHistory $result $newOperand"
        txt_result.text = ("" + currentResult)
        operand = newOperand
        mostReset = true

    }

    private fun doOperand(operand: String) {


        compute(operand)
    }
    private fun processEqual(nextOperand: String){
        compute(nextOperand)
        txt_history.text = ""
    }

    @SuppressLint("SetTextI18n")
    private fun appendNumber(num: Int){
        if (mostReset){
            txt_result.text = "0"
            mostReset = false
        }
        var oldValue = txt_result.text

        if (oldValue.length > 10){
            return
        }

        if (oldValue.equals("0")){
            if (num == 0){
                return
            }else{
                oldValue = ""
            }
        }

        txt_result.text = "$oldValue" + "$num"
    }

    private fun singed(){
        var resultValue = (txt_result.text).toString()
        resultValue = if (resultValue.contains("-")){
            resultValue.replace("-", "")

        }else{
            "-$resultValue"
        }
        txt_result.text = resultValue
    }

    private fun dot(){

        if (mostReset){
            txt_result.text = "0"

        }
        var resultValue = txt_result.text.toString()

        if (resultValue.length > 10){
            return
        }
        if (resultValue.contains(".")){
            return

        }else{
            resultValue += "."
        }
        txt_result.text = resultValue
    }

    private fun backOneLatter(){

        val resultValue = (txt_result.text).toString()
        if (resultValue == "0" || resultValue == "")return

        txt_result.text = resultValue.substring(0, resultValue.length-1)
    }


}