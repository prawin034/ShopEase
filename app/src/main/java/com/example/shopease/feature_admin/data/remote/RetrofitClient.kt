package com.example.shopease.feature_admin.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com"

    // -> 1 http logging Interceptor

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    // 2 -> OkhttpClient


    fun okhttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()
    }



     val retrofitInstance : ApiService  by lazy {
        createRetrofitClient()
    }


    fun createRetrofitClient() : ApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .build()

        return  retrofit.create(ApiService::class.java)
    }
}