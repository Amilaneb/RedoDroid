package com.example.restoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restoapp.databinding.ActivityCategoryBinding
import com.example.restoapp.databinding.ActivityHomeBinding
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.restoapp.model.Category
import com.example.restoapp.model.Dish
import com.example.restoapp.model.MenuResult
import com.google.gson.Gson
import org.json.JSONObject

enum class Cat {
    STARTER, DISH, DESSERT;

    companion object {
        fun getCastString(type: Cat): Int{
            return when(type){
                STARTER -> R.string.home_starters
                DISH -> R.string.home_dishes
                DESSERT -> R.string.home_desserts
            }
        }
    }
}

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var currentCategory: Cat
    private var cat: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? Cat?: Cat.STARTER

        setupTitle()
        postDataBouffe()

        Log.d("Debug", "Category")

    }

    override fun onStart() {
        Log.i("Debug", "start Cat")
        super.onStart()
    }
    private fun setupTitle(){
        binding.categoryTitle.text = getString(Cat.getCastString(currentCategory))
    }

    private fun postDataBouffe() {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jInput = JSONObject()
        jInput.put("id_shop","1")

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, jInput,
            { response ->
                //Log.d("CatA", "Response: %s".format(response.toString()))
                val menu = Gson().fromJson(response.toString(), MenuResult::class.java)
                //Log.d("CatA", "menu: %s".format(menu.toString()))
                displayMenu(menu)
            },
            Response.ErrorListener { error ->
                Log.e("CategoryActivity", "error : ${error.toString()}")
            }
        )

// Access the RequestQueue through your singleton class.
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }

    //private fun parseResult(){}

    private fun displayMenu(menu: MenuResult) {
        val categoryTitleList = menu.data[cat].dishes.map { it }
        //Log.d("CatA", "menu: %s".format(menu.data[cat].toString()))
        val plats: Category = menu.data[cat]
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter =
            CategoryAdapter(categoryTitleList) { item ->
            //Log.d("CatA", "item: %s".format(item.toString()))
            val intent = Intent( this, DetailActivity::class.java)
            //intent.putExtra("menu", plats)
            intent.putExtra("dish", item)
            startActivity(intent)
        }
    }
}