package com.example.restoapp.basket

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoapp.databinding.CellCategoryBinding
import com.example.restoapp.databinding.ItemCellsBinding
import com.squareup.picasso.Picasso

class BasketAdapter(private val items: List<BasketItem>, val deleteClickListener: (BasketItem)-> (Unit)): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){
    lateinit var context: Context
    class BasketViewHolder(view: ItemCellsBinding): RecyclerView.ViewHolder(view.root){
        val title: TextView = view.cellTitle
        val price: TextView = view.cellPrice
        val quantity: TextView = view.cellQty
        val delete: ImageButton = view.buttonDelete
        val image: ImageView = view.imageBasketDish
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        context = parent.context
        return BasketViewHolder(ItemCellsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItems = items[position]
        holder.title.text = basketItems.dish.title
        holder.quantity.text = basketItems.quantity.toString()
        holder.price.text = basketItems.dish.price.first().price
        holder.delete.setOnClickListener {
            deleteClickListener.invoke(basketItems)
        }
        Picasso.get()
            .load(basketItems.dish.getIMAGEurl())
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}