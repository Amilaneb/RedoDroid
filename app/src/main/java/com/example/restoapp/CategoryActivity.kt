package com.example.restoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryName = intent.getStringExtra("category")
        findViewById<TextView>(R.id.categoryTitle).text = categoryName
    }
}