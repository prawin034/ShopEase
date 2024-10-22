package com.example.shopease.feature_login.dataStore

import android.content.Context


fun saveToken(context: Context, token:String) {
    val sharedPrefs = context.getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
    sharedPrefs.edit()
        .putString("ACCESS_TOKEN",token)
        .apply()
}

fun getToken(context: Context) :String?{
    val sharedPreferences = context.getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
    return  sharedPreferences.getString("ACCESS_TOKEN","token")
}

