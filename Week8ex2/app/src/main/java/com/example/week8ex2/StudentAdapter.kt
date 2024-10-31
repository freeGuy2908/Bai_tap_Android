package com.example.week8ex2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.SinhVienViewHolder>() {

    inner class SinhVienViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHoTen: TextView = itemView.findViewById(R.id.tvHoTen)
        val tvMssv: TextView = itemView.findViewById(R.id.tvMssv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinhVienViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sinhvien, parent, false)
        return SinhVienViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinhVienViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvHoTen.text = student.name
        holder.tvMssv.text = student.mssv
    }

    override fun getItemCount(): Int = studentList.size

    fun updateList(newList: List<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }
}