package com.example.restoapp

import android.os.Bundle
import com.example.restoapp.basket.Basket
import com.example.restoapp.databinding.ActivityDetailBinding
import com.example.restoapp.model.Dish
import com.google.android.material.snackbar.Snackbar
import java.lang.Float.max

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var dish: Dish? = null
    private var qty = 1f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dish = intent.getSerializableExtra("dish") as? Dish
        binding.ingredList.text = dish?.getIngredientsString()
        binding.detailTitle.text = dish?.title
        binding.detailPrice.text = dish?.getPrice()
        dish?.images?.let {
            binding.carousel.adapter = ImageAdapter(this, it)
        }
        listenClicks()
        refreshTotal()

    }

    private fun listenClicks(){
        binding.buttonLess.setOnClickListener{
            qty = max(1f, qty - 1)
            binding.quantity.text = qty.toString()
            refreshTotal()
        }

        binding.buttonMore.setOnClickListener{
            qty++
            binding.quantity.text = qty.toString()
            refreshTotal()
        }

        binding.btnAdd.setOnClickListener{
            dish?.let{d->
                val basket = Basket.getBasket(this)
                basket.addItem(d, qty.toInt())
                basket.save(this)
                basket.updateCount(this)
                Snackbar.make(this, binding.detailView, "Successfully Added", 2000).show()
                invalidateOptionsMenu()
            }
        }

    }

    private fun refreshTotal() {
        dish?.let{ d ->
            val price: Float = d.price[0].price.toFloat()
            val totalPrice: Float = price * qty
            binding.btnAdd.text = "${getString(R.string.addBtn)} $totalPrice €"
            binding.quantity.text = qty.toInt().toString()
        }
    }

}