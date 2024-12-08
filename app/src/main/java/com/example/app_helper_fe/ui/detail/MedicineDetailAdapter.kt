package com.example.app_helper_fe.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R

class MedicineDetailAdapter(private var items: List<MedicineDetail>) : RecyclerView.Adapter<MedicineDetailAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.tv_category)
        val content: TextView = itemView.findViewById(R.id.tv_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.category.text = item.category
        holder.content.text = item.content
    }

    override fun getItemCount(): Int = items.size

    // 데이터를 갱신하는 메서드
    fun updateData(newItems: List<MedicineDetail>) {
        items = newItems
        notifyDataSetChanged() // RecyclerView 업데이트
    }
}
