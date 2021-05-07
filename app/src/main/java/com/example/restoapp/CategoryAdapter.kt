package com.example.restoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoapp.databinding.CellCategoryBinding
import com.squareup.picasso.Picasso
import com.example.restoapp.model.Dish


class CategoryAdapter(private val categories: List<Dish>, private val onItemClickListener: (Dish) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.textTitle.text = categories[position].title
        holder.textPrice.text = categories[position].price[0].toString()
        val imgView: ImageView = holder.Picture
        categories[position].getIMAGEurl()?.let { categories[position].displayIMG(it, imgView) }
        holder.layout.setOnClickListener {
            onItemClickListener.invoke(categories[position])
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(view: CellCategoryBinding) : RecyclerView.ViewHolder(view.root) {
        val textTitle: TextView = itemView.findViewById(R.id.cellCategoryTitle)
        val textPrice: TextView = itemView.findViewById(R.id.dishPrice)
        val Picture: ImageView = itemView.findViewById(R.id.dishImage)
        val layout = itemView.findViewById<View>(R.id.cellLayout)
    }



}