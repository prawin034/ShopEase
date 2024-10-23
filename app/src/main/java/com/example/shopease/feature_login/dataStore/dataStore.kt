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




fun saveTheme(context: Context,type: Boolean) {
    val sharedPrefs = context.getSharedPreferences("My_Prefs",Context.MODE_PRIVATE)
    val editor = sharedPrefs.edit()
    editor.putBoolean("APP_THEME",type)
    editor.apply()
}



fun getTheme(context: Context) : Boolean {

    val sharedPrefs = context.getSharedPreferences("My_Prefs",Context.MODE_PRIVATE)
    return  sharedPrefs.getBoolean("APP_THEME",false)
}