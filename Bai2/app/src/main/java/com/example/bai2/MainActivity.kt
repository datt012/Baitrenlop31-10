package com.example.bai2

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var radioGroupGioiTinh: RadioGroup
    private lateinit var edtEmail: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var txtNgaySinh: TextView
    private lateinit var checkBoxDongY: CheckBox

    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        radioGroupGioiTinh = findViewById(R.id.radioGroupGioiTinh)
        edtEmail = findViewById(R.id.edtEmail)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        txtNgaySinh = findViewById(R.id.txtNgaySinh)
        checkBoxDongY = findViewById(R.id.checkBoxDongY)

        findViewById<Button>(R.id.btnDatePicker).setOnClickListener {
            showDatePickerDialog()
        }

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            validateForm()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            txtNgaySinh.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun validateForm() {
        val mssv = edtMSSV.text.toString()
        val hoTen = edtHoTen.text.toString()
        val email = edtEmail.text.toString()
        val soDienThoai = edtSoDienThoai.text.toString()
        val gioiTinh = when (radioGroupGioiTinh.checkedRadioButtonId) {
            R.id.radioNam -> "Nam"
            R.id.radioNu -> "Nữ"
            else -> null
        }

        val errors = mutableListOf<String>()

        if (mssv.isEmpty()) errors.add("MSSV không được để trống.")
        if (hoTen.isEmpty()) errors.add("Họ tên không được để trống.")
        if (email.isEmpty()) errors.add("Email không được để trống.")
        if (soDienThoai.isEmpty()) errors.add("Số điện thoại không được để trống.")
        if (gioiTinh == null) errors.add("Vui lòng chọn giới tính.")
        if (selectedDate.isEmpty()) errors.add("Vui lòng chọn ngày sinh.")
        if (!checkBoxDongY.isChecked) errors.add("Bạn cần đồng ý với các điều khoản.")

        if (errors.isNotEmpty()) {
            Toast.makeText(this, errors.joinToString("\n"), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Gửi thông tin thành công!", Toast.LENGTH_SHORT).show()
        }
    }
}