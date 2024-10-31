package com.example.bai3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample data for students
        studentList = listOf(
            Student("Nguyen Van A", "123456"),
            Student("Le Thi B", "654321"),
            Student("Tran Van C", "111222"),
            Student("Pham Thi D", "333444")
            // Add more student entries as needed
        )

        // Setup ListView
        val listView = findViewById<ListView>(R.id.listView)
        val studentNames = studentList.map { "${it.name} - ${it.id}" }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
        listView.adapter = adapter

        // Setup search functionality
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                if (keyword.length > 2) {
                    filterList(keyword)
                } else {
                    adapter.clear()
                    adapter.addAll(studentNames) // Show full list if keyword has less than 3 characters
                }
            }
        })
    }

    // Filter function
    private fun filterList(keyword: String) {
        val filteredNames = studentList.filter {
            it.name.contains(keyword, ignoreCase = true) || it.id.contains(keyword, ignoreCase = true)
        }.map { "${it.name} - ${it.id}" }
        adapter.clear()
        adapter.addAll(filteredNames)
    }
}
data class Student(val name: String, val id: String)