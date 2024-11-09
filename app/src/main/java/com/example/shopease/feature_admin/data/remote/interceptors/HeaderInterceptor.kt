package com.example.shopease.feature_admin.data.remote.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val isNeutrinoService :Boolean ,private val token :String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
        if (token != null && !isNeutrinoService) {
            newRequest.addHeader("Authorization", "Bearer $token")
        }
        if(token !=null && isNeutrinoService) {
            newRequest.addHeader("API-Key", token)
            newRequest.addHeader("User-ID", "PRAWINKUMAR")
        }
        newRequest.addHeader("Content-Type", "application/json")

        val buildRequest = newRequest.build()
        for(header in buildRequest.headers) {
            Log.d("Headers","$header")
        }
        return chain.proceed(buildRequest)
    }
}