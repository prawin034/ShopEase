package com.example.shopease.feature_admin.data.remote

import com.example.shopease.feature_admin.data.model.AddToCartRequest
import com.example.shopease.feature_admin.data.model.AddToCartResponse
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_admin.data.model.SearchProducts
import com.example.shopease.feature_admin.data.model.UpdateCartRequest
import com.example.shopease.feature_login.model.RegisterRequest
import com.example.shopease.feature_login.model.loginRequest
import com.example.shopease.feature_login.model.loginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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



    @POST("/carts/add")
    suspend fun addToCart(@Body payload : AddToCartRequest) : Response<AddToCartResponse>

    @DELETE("/carts/{id}")
    suspend fun deleteCart(@Path("id")id: Int) :Response<AddToCartResponse>

    @PUT("/carts/{id}")
    suspend fun updateCart(@Path("id") id: Int, @Body payload:UpdateCartRequest) :Response<AddToCartResponse>


    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query:String,
        @Query("sortBy") sortBy :String,
        @Query("order") order:String,
    ) : Response<SearchProducts>

}

//https://dummyjson.com/products/search?q=laptop&sortBy=id&order=desc