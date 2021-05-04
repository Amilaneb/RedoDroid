package com.example.restoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.restoapp.databinding.ActivityCategoryBinding
import com.example.restoapp.databinding.ActivityDetailBinding
import com.example.restoapp.model.Dish

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dishes = intent.getSerializableExtra("menu")
        val dishTitle = intent.getStringExtra("name")
        Log.d("detail", "Response: %s".format(dishes.toString()))
        Log.d("detail", "Response: %s".format(dishTitle.toString()))
    }
}