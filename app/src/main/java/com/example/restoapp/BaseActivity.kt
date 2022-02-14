package com.example.restoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.example.restoapp.basket.Basket
import com.example.restoapp.basket.BasketActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuView = menu?.findItem(R.id.basketicon)?.actionView
        val countText = menuView?.findViewById(R.id.items) as? TextView
        val count = getCount()
        countText?.isVisible = count > 0
        countText?.text = count.toString()


        menuView?.setOnClickListener{
            if(count > 0){
                val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    }



    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    private fun getCount():Int{
        val sharedPreferences = getSharedPreferences(Basket.USER_PREFERENCE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Basket.ITEMS_COUNT, 0)
    }
}