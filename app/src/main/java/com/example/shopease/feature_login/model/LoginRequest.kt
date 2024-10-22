package com.example.shopease.feature_login.model





data class loginRequest(
    val username :String,
    val password :String,
    val expiresInMins : Int,
)