package com.example.restoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.restoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainAction = findViewById<Button>(R.id.starters)

        binding.starters.setOnClickListener{
            val intnt = Intent(this@MainActivity, CategoryActivity::class.java)
            intnt.putExtra("category", "Entrées")
            startActivity(intnt)
        }

        binding.dishes.setOnClickListener{
            val intnt = Intent(this@MainActivity, CategoryActivity::class.java)
            intnt.putExtra("category", "Plats")
            startActivity(intnt)
        }

        binding.desserts.setOnClickListener{
            val intnt = Intent(this@MainActivity, CategoryActivity::class.java)
            intnt.putExtra("category", "Desserts")
            startActivity(intnt)
        }
    }
}