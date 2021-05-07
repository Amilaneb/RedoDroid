package com.example.restoapp.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.squareup.picasso.Picasso


data class Dish(@SerializedName("name_fr") val title: String,
                val ingredients: List<Ingredients>,
                val images: List<String>,
@SerializedName("prices")val price: List<Price>): Serializable {

    fun getIMAGEurl(): String? {
        return images[0]
    }

    fun displayIMG(url: String, view: ImageView) {
        if (url?.isNotEmpty()) {
            Picasso.get().load(url).into(view)
        }
    }

    fun getIngredientsString(ingreds: List<Ingredients>): String {
        return ingreds.joinToString(", ") { it.name }
    }
}