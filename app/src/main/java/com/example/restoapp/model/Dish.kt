package com.example.restoapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dish(@SerializedName("name_fr") val title: String,
                val ingredients: List<Ingredients>,
                val images: List<String>,
                @SerializedName("prices")val price: List<Price>): Serializable