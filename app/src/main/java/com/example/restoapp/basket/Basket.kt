package com.example.restoapp.basket

import com.example.restoapp.model.Dish
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.Serializable

class Basket(val items: MutableList<BasketItem>): Serializable {

    var itemcount: Int = 0
        get() {
        /* val count =*/    return items.map {
                it.quantity
            }.reduceOrNull { acc, i -> acc + i } ?: 0 //return count ?: 0  (si null alors = 0)
        }

    var totalPrice: Float = 0F
        get() {
            return items.map {
                it.dish.price.first().price.toFloat()
            }.reduceOrNull { acc, fl -> acc + fl } ?: 0f //return count ?: 0  (si null alors = 0)
        }

    fun addItem(dish: Dish, quantity: Int){
        val existingItem = items.firstOrNull {it.dish.title == dish.title}
        existingItem?.let {
            existingItem.quantity += quantity
        } ?: run {
            val basketItem = BasketItem(dish, quantity)
            items.add(basketItem)
        }
    }

    fun removeItem(basketItem: BasketItem){
        items.remove(basketItem)
    }



    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        updateCount(context)
    }

    fun updateCount(context: Context) {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCE_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemcount)
        editor.apply()
    }



    companion object {
        fun getBasket(context: Context): Basket{
            val jsonfile = File(context.cacheDir.absolutePath + BASKET_FILE)
            return if(jsonfile.exists()){
                val json = jsonfile.readText()
                GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                Basket (mutableListOf())
            }
        }

        const val BASKET_FILE = "basket.json"
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCE_NAME = "USER_PREFERENCE_NAME"
    }
}

class BasketItem(val dish: Dish, var quantity: Int): Serializable {

}