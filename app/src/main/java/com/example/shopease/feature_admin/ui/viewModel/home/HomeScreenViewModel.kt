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


    private val _getProductBasedOnHotSalesCategory = mutableStateListOf<ProductCategory>()
    val getProductBasedOnHotSalesCategory : List<ProductCategory> get() = _getProductBasedOnHotSalesCategory


    // -> Beauty

    private val _getProductBasedOnBeautyCategory = mutableStateListOf<ProductCategory>()
    val getProductBasedOnBeautyCategory : List<ProductCategory> get() = _getProductBasedOnBeautyCategory


    //-> Furniture

    private val _getProductBasedOnFurnitureCategory = mutableStateListOf<ProductCategory>()
    val getProductBasedOnFurnitureCategory : List<ProductCategory> get() = _getProductBasedOnFurnitureCategory



    //--> Grocery
    private val _getProductBasedOnGroceriesCategory = mutableStateListOf<ProductCategory>()
    val getProductBasedOnGroceriesCategory : List<ProductCategory> get() = _getProductBasedOnGroceriesCategory



    private val _getProductBasedOnWomens_dressesCategory = mutableStateListOf<ProductCategory>()
    val getProductBasedOnWomens_dressesCategory : List<ProductCategory> get() = _getProductBasedOnWomens_dressesCategory


    private val _getSingleProduct = mutableStateListOf<Product>()
    val getSingleProduct :List<Product> get() = _getSingleProduct


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
                    _getProductBasedOnHotSalesCategory.clear()
                    _getProductBasedOnHotSalesCategory.add(response)

                }
            }
        }
    }

    fun getProductBasedOnBeautyCategory(category:String){
        viewModelScope.launch {
            val result = repository.getProductBasedOnCategory(category)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getProductBasedOnBeautyCategory.clear()
                    _getProductBasedOnBeautyCategory.add(response)

                }
            }
        }
    }

    fun getProductBasedOnFurnitureCategory(category:String){
        viewModelScope.launch {
            val result = repository.getProductBasedOnCategory(category)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getProductBasedOnFurnitureCategory.clear()
                    _getProductBasedOnFurnitureCategory.add(response)

                }
            }
        }
    }

    fun getProductBasedOnGroceryCategory(category:String){
        viewModelScope.launch {
            val result = repository.getProductBasedOnCategory(category)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getProductBasedOnGroceriesCategory.clear()
                    _getProductBasedOnGroceriesCategory.add(response)

                }
            }
        }
    }
    fun getProductBasedOnWomenDressCategory(category:String){
        viewModelScope.launch {
            val result = repository.getProductBasedOnCategory(category)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getProductBasedOnWomens_dressesCategory.clear()
                    _getProductBasedOnWomens_dressesCategory.add(response)

                }
            }
        }
    }



    fun getSingleProduct(id:Int){
        viewModelScope.launch {
            val result = repository.getSingleProduct(id)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _getSingleProduct.clear()
                    _getSingleProduct.add(response)
                }
            }
            else{
                val exception = result.exceptionOrNull()
                _failureMessage.value = "Failed ${exception?.message}"
            }
        }
    }

}



//[
//"beauty",
//"fragrances",
//"furniture",
//"groceries",
//"home-decoration",
//"kitchen-accessories",
//"laptops",
//"mens-shirts",
//"mens-shoes",
//"mens-watches",
//"mobile-accessories",
//"motorcycle",
//"skin-care",
//"smartphones",
//"sports-accessories",
//"sunglasses",
//"tablets",
//"tops",
//"vehicle",
//"womens-bags",
//"womens-dresses",
//"womens-jewellery",
//"womens-shoes",
//"womens-watches"
//]