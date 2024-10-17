package com.example.shopease.feature_admin.data.remote

import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory


class ApiRepository {


    private val apiService : ApiService = RetrofitClient.retrofitInstance



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




}