package com.example.shopease.feature_admin.data.remote

import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/products/categories")
    suspend fun getAllProductsCategory() : Response<List<AllProductCategory>>

    @GET("/products/category/{category}")
    suspend fun getProductBasedOnCategory(@Path("category") category :String) : Response<ProductCategory>


    @GET("/products/{id}")
    suspend fun getSingleProduct(@Path("id") id:Int) : Response<Product>

}