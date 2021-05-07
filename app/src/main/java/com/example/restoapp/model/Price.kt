package com.example.restoapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Price (@SerializedName("price") val price: String): Serializable {
    override fun toString(): String {
        return "$price€"
    }
}