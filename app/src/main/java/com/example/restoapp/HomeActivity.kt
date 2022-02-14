package com.example.restoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import com.example.restoapp.databinding.ActivityHomeBinding
import java.util.*

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

            setListener()
        /*binding.bleScanBtn.setOnClickListener{
            val intent = Intent(this@HomeActivity, BleScanActivity::class.java)
            startActivity(intent)
        }*/


    }
    companion object {
        const val CategoryType = "CategoryType"
    }

    private fun setListener(){
        binding.starters.setOnClickListener{
            PassCat(Cat.STARTER)
        }

        binding.dishes.setOnClickListener{
            PassCat(Cat.DISH)
        }

        binding.desserts.setOnClickListener{
            PassCat(Cat.DESSERT)
        }
    }

    private fun PassCat(item: Cat){

            val i = Intent(this@HomeActivity, CategoryActivity::class.java)
            i.putExtra(CategoryType , item)
            Log.d("Debug", "start!!")
            startActivity(i)
        }

    override fun onDestroy() {
        Log.i("destroy", "Home detruit")
        super.onDestroy()

    }
}