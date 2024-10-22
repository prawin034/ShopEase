package com.example.shopease.feature_admin.data.remote

import android.content.Context
import android.util.Log
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_login.model.RegisterRequest
import com.example.shopease.feature_login.model.loginRequest
import com.example.shopease.feature_login.model.loginResponse


class ApiRepository(private val context: Context) {


    private val apiService : ApiService = RetrofitClient.createRetrofitClient(context)



    /* Login Panel

     */
    suspend fun login(payload:loginRequest) : Result<loginResponse> {
        return  try {
            val  response = apiService.login(payload)
            if(response.isSuccessful) {
                val responseBody = response.body()
                if(responseBody !=null) {
                    Result.success(responseBody)
                }
                else {
                    Result.failure(Exception("Response body is null"))
                }
            }
            else {
                Result.failure(Exception("Response body is failed with code ${response.code()}"))
            }
        }catch (e:Exception){
            Result.failure(Exception("Request failed :${e.message}"))
        }
    }


    suspend fun register(payload:RegisterRequest) : Result<RegisterRequest> {
        return  try {
            val  response = apiService.register(payload)
            if(response.isSuccessful) {
                val responseBody = response.body()
                if(responseBody !=null) {
                    Result.success(responseBody)
                }
                else {
                    Result.failure(Exception("Response body is null"))
                }
            }
            else {
                Result.failure(Exception("Response body is failed with code ${response.code()}"))
            }
        }catch (e:Exception){
            Result.failure(Exception("Request failed :${e.message}"))
        }
    }




    /* Admin panel

     */
    suspend fun getAllProductCategory() : Result<List<AllProductCategory>> {
        return  try {
            val response = apiService.getAllProductsCategory()
            if(response.isSuccessful) {
                val shopEaseResponse = response.body()
                if(shopEaseResponse !=null) Result.success(shopEaseResponse)
                else Result.failure(Exception("Get All product category response body is null"))
            }
            else{
                Result.failure(Exception(" Request failed with code ${response.code()}"))
            }

        }catch (e:Exception){
                Result.failure(Exception("Request failed: ${e.message}"))
        }

    }
    suspend fun getProductBasedOnCategory(category:String) : Result<ProductCategory> {
        return  try {
            val response = apiService.getProductBasedOnCategory(category)
            if(response.isSuccessful) {
                val shopEaseResponse = response.body()
                if(shopEaseResponse !=null) Result.success(shopEaseResponse)
                else Result.failure(Exception("Get  product based on  category response body is null"))
            }
            else{
                Result.failure(Exception(" Request failed with code ${response.code()}"))
            }

        }catch (e:Exception){
            Result.failure(Exception("Request failed: ${e.message}"))
        }

    }

    suspend fun getSingleProduct(id:Int) : Result<Product> {
        return  try {
            val response = apiService.getSingleProduct(id)
            if(response.isSuccessful) {
                val shopEaseResponse = response.body()
                if(shopEaseResponse !=null) Result.success(shopEaseResponse)
                else Result.failure(Exception("Get  product based on  category response body is null"))
            }
            else{
                Result.failure(Exception(" Request failed with code ${response.code()}"))
            }

        }catch (e:Exception){
            Result.failure(Exception("Request failed: ${e.message}"))
        }

    }




}