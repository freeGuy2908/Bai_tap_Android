package com.example.week8ex2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week8ex2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentAdapter
    private lateinit var sinhVienList: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dữ liệu mẫu
        sinhVienList = mutableListOf(
            Student("Nguyễn Văn A", "20210001"),
            Student("Trần Thị B", "20210002"),
            Student("Lê Văn C", "20210003"),
            Student("Phạm Thị D", "20210004"),
            Student("Hoàng Văn E", "20210005")
        )

        adapter = StudentAdapter(sinhVienList)
        binding.rvStudents.layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter

        // Thêm TextWatcher vào EditText để tìm kiếm
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.length > 2) {
                    searchStudents(query)
                } else {
                    adapter.updateList(sinhVienList) // Hiển thị toàn bộ danh sách khi không tìm kiếm
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Tìm kiếm sinh viên theo tên hoặc MSSV
    private fun searchStudents(query: String) {
        val filteredList = sinhVienList.filter {
            it.name.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }
}