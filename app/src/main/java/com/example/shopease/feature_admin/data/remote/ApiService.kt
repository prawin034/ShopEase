package com.example.shopease.feature_admin.data.remote

import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_login.model.RegisterRequest
import com.example.shopease.feature_login.model.loginRequest
import com.example.shopease.feature_login.model.loginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    /* Login Service Calls

     */

    @POST("/auth/login")
    suspend fun login(@Body payload : loginRequest) : Response<loginResponse>

    @POST("/users/add")
    suspend fun register(@Body payload:RegisterRequest) :Response<RegisterRequest>



    /* Admin Service Calls

     */
    @GET("/products/categories")
    suspend fun getAllProductsCategory() : Response<List<AllProductCategory>>

    @GET("/products/category/{category}")
    suspend fun getProductBasedOnCategory(@Path("category") category :String) : Response<ProductCategory>
    @GET("/products/{id}")
    suspend fun getSingleProduct(@Path("id") id:Int) : Response<Product>

}