package com.example.restoapp.user

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.restoapp.Network.NetworkConstants
import com.example.restoapp.R
import com.example.restoapp.databinding.ActivityUserBinding
import com.google.gson.GsonBuilder
import org.json.JSONObject

interface UserActivityFragmentInteraction {
    fun showlogin()
    fun showregister()
    fun makerequest(email: String?, password: String?, firstname: String?, lastname: String?, isFromlogin: Boolean)
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteraction {
    lateinit var binding: ActivityUserBinding
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    override fun showlogin(){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit()
    }

    override fun showregister(){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, registerFragment).commit()
    }

    override fun makerequest(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromlogin: Boolean
    ) {
        if(verifyinfo(email, password, lastname, firstname, isFromlogin)){
            launchRequest(email,password,firstname,lastname, isFromlogin)
        }else{
            Toast.makeText(this, getString(R.string.invalidForms), Toast.LENGTH_LONG).show()
        }
    }

    fun launchRequest(email: String?,
                      password: String?,
                      firstname: String?,
                      lastname: String?,
                      isFromlogin: Boolean){
        val queue = Volley.newRequestQueue(this)
        var reqURL = NetworkConstants.URL
        if (isFromlogin){
            reqURL += NetworkConstants.USER_LOGIN
        } else {
            reqURL += NetworkConstants.USER_REGISTER
        }

        val params = JSONObject()
        params.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        params.put(NetworkConstants.KEY_EMAIL, email)
        params.put(NetworkConstants.KEY_PWD, password)
        if(!isFromlogin) {
            params.put(NetworkConstants.KEY_FIRSTNAME, firstname)
            params.put(NetworkConstants.KEY_LASTNAME, lastname)
        }

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, reqURL, params,
            {response ->
                val userResult = GsonBuilder().create().fromJson(response.toString(), UserResult::class.java)
                if(userResult.data != null){

                saveuser(userResult.data)
                Log.d("signin", "${response.toString(2)}")

                }
                else {
                    Toast.makeText(this, getString(R.string.invalidForms), Toast.LENGTH_LONG).show()
                }
            },
            {
                Log.d("signin", "error : ${it.message}")
            }
        )
        queue.add(jsonRequest)
    }

    private fun saveuser(user: User){
        val sharedPreference = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt(ID_USER, user.id)
        editor.apply()

        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun verifyinfo(email: String?,
                           password: String?,
                           firstname: String?,
                           lastname: String?,
                            isFromlogin: Boolean): Boolean
    {
        var verified = (email?.isNotEmpty() == true && password?.count() ?: 0 >= 6)

        if (!isFromlogin) {
            verified = verified && (firstname?.isNotEmpty() == true && lastname?.isNotEmpty() == true)
        }
        return verified

    }

    companion object {
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
        const val ID_USER = "ID_USER"
    }
}