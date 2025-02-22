package com.example.shopease.feature_admin.ui.viewModel.seach

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.model.SearchProducts
import com.example.shopease.feature_admin.data.remote.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val respository: ApiRepository) : ViewModel() {

    private val _searchProducts = MutableLiveData<SearchProducts>()
    val searchProducts : LiveData<SearchProducts> get() = _searchProducts

    private val _successMsg = MutableLiveData<String?>(null)
    val successMsg : LiveData<String?> get() = _successMsg

    private val _failureMsg = MutableLiveData<String?>(null)
    val failureMsg : LiveData<String?> get() = _failureMsg

    fun searchProducts(query:String,sortBy:String,order:String) {
        _successMsg.value = null
        _failureMsg.value = null
        viewModelScope.launch {
            val result = respository.searchProducts(query,sortBy,order)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    response.let {
                        _searchProducts.postValue(it)
                        _successMsg.value = "SuccessFully Loaded!"
                    }
                }
            }
            else{
                val exception = result.exceptionOrNull()
                _failureMsg.value = "Error : ${exception?.message}"
            }
        }
    }





    private val _checkList = mutableStateListOf<String>()
    val checkList : List<String> get() = _checkList


    private val _brandsList = mutableStateListOf<String>()
    val brandsList : List<String> get() = _brandsList


    fun removeCheckListItem(item:String){
        _checkList.remove(item)
    }
    fun addCheckListItem(item:String){
        _checkList.add(item)
    }
    fun clearCheckList(){
        _checkList.clear()
    }
    fun updateBrandList(item:String){

        _brandsList.add(item)
    }
    fun clearBrandList(){
        _brandsList.clear()
    }


    //This function filters list based on brands
    fun FilterProducts(
        query:String,
        sortBy:String,
        order:String,
        selectedBrand:List<String>
    ) {
        _successMsg.value = null
        _failureMsg.value = null
        viewModelScope.launch {
            val result = respository.searchProducts(query,sortBy,order)
            if(result.isSuccess) {
                val response = result.getOrNull()
                response.let { it ->
                    val updatedList = it?.products?.filter { it.brand in selectedBrand }
                    _searchProducts.postValue(SearchProducts(updatedList ?: emptyList()))
                    _successMsg.value = "SuccessFully Loaded!"
                }

            }
            else{
                val exception = result.exceptionOrNull()
                _failureMsg.value = "Error : ${exception?.message}"
            }
        }
    }

}





@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(private  val respository :ApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(respository) as T
        }

        throw IllegalArgumentException("Unknown class")
    }
}