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


    private fun okhttpClient(context: Context, token: String?,isNeutrinoService:Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor(isNeutrinoService,token))
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()
    }



//     val retrofitInstance : ApiService by lazy {
//        createRetrofitClient()
//    }
    fun<T> createRetrofitClient(
    context: Context,
    baseUrl :String = BASE_URL,serviceClass:Class<T>,
    token : String? =null,
    isNeutrinoService: Boolean,
    ) : T {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context,token,isNeutrinoService))
            .build()
        return  retrofit.create(serviceClass)
    }
}