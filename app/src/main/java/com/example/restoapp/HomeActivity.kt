package com.example.restoapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
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
        val nightMode = binding.nightModeBtn
        val appSettingPrefs : SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val editor : SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn : Boolean = appSettingPrefs.getBoolean("NightMode", false)

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        nightMode.setOnClickListener {
            if(isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, "nightModeON", Toast.LENGTH_LONG).show()
                editor.putBoolean("NightMode", false)
                editor.apply()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, "nightModeOFF", Toast.LENGTH_LONG).show()
                editor.putBoolean("NightMode", true)
                editor.apply()
            }
        }


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