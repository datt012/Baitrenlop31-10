package com.example.bai1

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Components
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            if (input.isEmpty()) {
                textViewError.text = "Please enter a positive integer"
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Please enter a valid positive integer"
                return@setOnClickListener
            }

            // Reset error message
            textViewError.text = ""

            // Calculate numbers based on selected type
            val numbers = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            // Display numbers in ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}