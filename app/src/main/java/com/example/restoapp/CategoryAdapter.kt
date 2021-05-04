package com.example.restoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoapp.databinding.CellCategoryBinding


class CategoryAdapter(private val categories: List<String>, private val onItemClickListener: (String) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = categories[position]
        holder.layout.setOnClickListener {
            onItemClickListener(categories[position])
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cellCategoryTitle)
        val layout = view.findViewById<View>(R.id.cellLayout)
    }



}