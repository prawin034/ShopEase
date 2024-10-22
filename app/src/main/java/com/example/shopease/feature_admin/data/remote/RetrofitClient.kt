package com.example.shopease.feature_admin.data.remote

import android.content.Context
import com.example.shopease.feature_admin.data.remote.interceptors.HeaderInterceptor
import com.example.shopease.feature_login.dataStore.getToken
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


    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor(getToken(context)))
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()
    }



//     val retrofitInstance : ApiService by lazy {
//        createRetrofitClient()
//    }




    fun createRetrofitClient(context: Context) : ApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context))
            .build()

        return  retrofit.create(ApiService::class.java)
    }
}