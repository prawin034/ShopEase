package com.example.shopease.feature_admin.ui.viewModel.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_admin.data.remote.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(
    private val repository: ApiRepository
) : ViewModel()
{






    private val _getAllProductsCategory = MutableLiveData<List<AllProductCategory>?>(emptyList())
    val getAllProductCategory: LiveData<List<AllProductCategory>?> get() = _getAllProductsCategory

    private val _getProductBasedOnHotSalesCategory = MutableLiveData<List<ProductCategory>?>(emptyList())
    val getProductBasedOnHotSalesCategory: LiveData<List<ProductCategory>?> get() = _getProductBasedOnHotSalesCategory

    private val _getProductBasedOnCategory = MutableLiveData<ProductCategory>()
    val getProductBasedOnCategory: LiveData<ProductCategory> get() = _getProductBasedOnCategory

    private val _getProductBasedOnBeautyCategory = MutableLiveData<List<ProductCategory>?>(emptyList())
    val getProductBasedOnBeautyCategory: LiveData<List<ProductCategory>?> get() = _getProductBasedOnBeautyCategory

    private val _getProductBasedOnFurnitureCategory = MutableLiveData<List<ProductCategory>?>(emptyList())
    val getProductBasedOnFurnitureCategory: LiveData<List<ProductCategory>?> get() = _getProductBasedOnFurnitureCategory

    private val _getProductBasedOnGroceriesCategory = MutableLiveData<List<ProductCategory>?>(emptyList())
    val getProductBasedOnGroceriesCategory: LiveData<List<ProductCategory>?> get() = _getProductBasedOnGroceriesCategory

    private val _getProductBasedOnWomens_dressesCategory = MutableLiveData<List<ProductCategory>?>(emptyList())
    val getProductBasedOnWomens_dressesCategory: LiveData<List<ProductCategory>?> get() = _getProductBasedOnWomens_dressesCategory


    private val _getSingleProduct = MutableLiveData<List<Product>?>()
    val getSingleProduct :LiveData<List<Product>?> get() = _getSingleProduct


    private val _successMessage = mutableStateOf<String?>(null)
    val successMessage : State<String?> get() = _successMessage


    private val _failureMessage = mutableStateOf<String?>(null)
    val failureMessage : State<String?> get() = _failureMessage



    /* Search Paramters


     */
    private val _querySearch = MutableLiveData<String>()
    val querySearch : LiveData<String> get() = _querySearch
    fun updateQuerySearch(value:String){
        _querySearch.value = value
    }

    private val _activeSearch = MutableLiveData<Boolean>(false)
    val activeSearch  : LiveData<Boolean> get() = _activeSearch


    fun updateActiveSearch(value:Boolean){
        _activeSearch.value = value
    }



    private val _searchHistoryList = mutableStateListOf<String>()
    val searchHistoryList : List<String> get() = _searchHistoryList



    fun addSearchHistory(query :String) {
        _searchHistoryList.add(query)
    }






     fun getAllProductsCategory()  {
        _successMessage.value = null
        _failureMessage.value = null
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getAllProductCategory()
            }

            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    _getAllProductsCategory.postValue(response)
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
            val result = withContext(Dispatchers.IO) {
                repository.getProductBasedOnCategory(category)
            }
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    _getProductBasedOnHotSalesCategory.postValue(listOf(response))

                }
            }
        }
    }

    fun getProductBasedCategory(category:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getProductBasedOnCategory(category)
            }
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    response?.let {
                        _getProductBasedOnCategory.postValue(it)
                    }
                }
            }
        }
    }

    fun getProductBasedOnBeautyCategory(category:String){
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                repository.getProductBasedOnCategory(category)
            }
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    _getProductBasedOnBeautyCategory.postValue(listOf(response))

                }
            }
        }
    }

    fun getProductBasedOnFurnitureCategory(category:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getProductBasedOnCategory(category)
            }
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    _getProductBasedOnFurnitureCategory.postValue(listOf(response))

                }
            }
        }
    }

    fun getProductBasedOnGroceryCategory(category:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getProductBasedOnCategory(category)
            }
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    _getProductBasedOnGroceriesCategory.postValue(listOf(response))

                }
            }
        }
    }
    fun getProductBasedOnWomenDressCategory(category:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getProductBasedOnCategory(category)
            }
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {

                    _getProductBasedOnWomens_dressesCategory.postValue(listOf(response))

                }
            }
        }
    }


    fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.getSingleProduct(id) }

            if (result.isSuccess) {
                result.getOrNull()?.let { response ->
                    _getSingleProduct.postValue(listOf(response)) // assuming _getSingleProduct is MutableLiveData<Product?>
                }
            } else {
                val exception = result.exceptionOrNull()
                _failureMessage.value = "Failed: ${exception?.localizedMessage ?: "Unknown Error"}"
            }
        }
    }


//    fun getSingleProduct(id:Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            val result = repository.getSingleProduct(id)
//            if(result.isSuccess) {
//                val response = result.getOrNull()
//                if(response !=null) {
//                    withContext(Dispatchers.Main) {
//                        _getSingleProduct.clear()
//                        _getSingleProduct.add(response)
//                    }
//
//                }
//            }
//            else{
//                val exception = result.exceptionOrNull()
//                _failureMessage.value = "Failed ${exception?.message}"
//            }
//        }
//    }

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