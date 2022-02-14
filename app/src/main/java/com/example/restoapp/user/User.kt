package com.example.restoapp.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User(@SerializedName("id") val id: Int): Serializable {}

class UserResult(@SerializedName("data") val data: User) {}