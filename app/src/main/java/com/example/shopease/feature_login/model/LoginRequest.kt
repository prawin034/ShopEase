package com.example.shopease.feature_login.model





data class loginRequest(
    val username :String,
    val password :String,
    val expiresInMins : Int,
)



data class RegisterRequest(

    val firstName :String,
    val username: String,
    val lastName :String,
    val email :String,
    val password: String,
)

