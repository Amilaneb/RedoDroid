package com.example.restoapp.basket

import android.app.Activity
import android.bluetooth.BluetoothHidDeviceAppQosSettings
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restoapp.databinding.ActivityBasketBinding
import com.example.restoapp.user.UserActivity

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonOrder.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            getResult.launch(intent)
        }
        loadList()

    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK)
        {
            Toast.makeText(this, "Send Order", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadList(){
        val basket: Basket = Basket.getBasket(this)
        val items = basket.items
        binding.basketRecyclerView.layoutManager =  LinearLayoutManager(this)
        binding.basketRecyclerView.adapter = BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadList()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){

        }
    }

    companion object {
        const val REQUEST_CODE = 111
    }
}

