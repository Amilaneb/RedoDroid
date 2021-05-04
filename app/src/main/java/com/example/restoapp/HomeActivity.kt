package com.example.restoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.restoapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainAction = findViewById<Button>(R.id.starters)


        binding.starters.setOnClickListener{
            val intnt = Intent(this@HomeActivity, CategoryActivity::class.java)
            intnt.putExtra("category", "Entrées")
            startActivity(intnt)
        }

        binding.dishes.setOnClickListener{
            val intnt = Intent(this@HomeActivity, CategoryActivity::class.java)
            intnt.putExtra("category", "Plats")
            startActivity(intnt)
        }

        binding.desserts.setOnClickListener{
            val intnt = Intent(this@HomeActivity, CategoryActivity::class.java)
            intnt.putExtra("category", "Desserts")
            startActivity(intnt)
        }

        binding.bleScanBtn.setOnClickListener{
            val intnt = Intent(this@HomeActivity, BleScanActivity::class.java)
            startActivity(intnt)
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("destroy", "Home detruit")
    }
}