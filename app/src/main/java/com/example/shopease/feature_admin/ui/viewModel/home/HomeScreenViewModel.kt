package com.example.shopease.feature_admin.ui.viewModel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_admin.data.remote.ApiRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(val repository: ApiRepository) : ViewModel() {


    private val _getAllProductsCategory = mutableStateListOf<AllProductCategory>()
    val getAllProductCategory : List<AllProductCategory> get() = _getAllProductsCategory



    private val _getProductBasedOnCategory = mutableStateListOf<ProductCategory>()
    val getProductBasedOnCategory : List<ProductCategory> get() = _getProductBasedOnCategory

    private val _successMessage = mutableStateOf<String?>(null)
    val successMessage : State<String?> get() = _successMessage


    private val _failureMessage = mutableStateOf<String?>(null)
    val failureMessage : State<String?> get() = _failureMessage





    fun getAllProductsCategory()  {
        _successMessage.value = null
        _failureMessage.value = null
        viewModelScope.launch {
            val result  = repository.getAllProductCategory()
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getAllProductsCategory.clear()
                    _getAllProductsCategory.addAll(response)
                    _successMessage.value = "SuccessFully loaded"
                }
                else {
                    _failureMessage.value = "failed to load"
                }
            }
            else {
                val exception = result.exceptionOrNull()
                _failureMessage.value = "failed : ${exception?.message}"
            }

        }

    }



    fun getProductBasedOnCategory(category:String){
        viewModelScope.launch {
            val result = repository.getProductBasedOnCategory(category)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getProductBasedOnCategory.clear()
                    _getProductBasedOnCategory.add(response)

                }
            }
        }
    }



}