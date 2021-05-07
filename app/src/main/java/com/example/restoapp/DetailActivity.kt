package com.example.restoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.restoapp.databinding.ActivityCategoryBinding
import com.example.restoapp.databinding.ActivityDetailBinding
import com.example.restoapp.model.Dish
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dish: Dish = intent.getSerializableExtra("dish") as Dish
        val imageView: ImageView //= findViewById(R.id.detailImageView)
        val url: String? = dish.getIMAGEurl()
        val carousel: PhotoAdapter =
        binding.ingredList.text = dish.getIngredientsString(dish.ingredients)
        binding.carousel
    }

}