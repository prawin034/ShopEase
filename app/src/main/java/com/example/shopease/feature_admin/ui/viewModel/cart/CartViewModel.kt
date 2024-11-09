package com.example.shopease.feature_admin.ui.viewModel.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.model.AddToCartRequest
import com.example.shopease.feature_admin.data.model.AddToCartResponse
import com.example.shopease.feature_admin.data.model.UpdateCartRequest
import com.example.shopease.feature_admin.data.remote.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: ApiRepository) :ViewModel() {

    private val _cartList = MutableLiveData<List<AddToCartResponse>?>()
    val cartList : LiveData<List<AddToCartResponse>?> get() = _cartList


    private val _successMessage = MutableLiveData<String?>()
    val successMessage : LiveData<String?> get() = _successMessage


    private val _failureMessage = MutableLiveData<String?>()
    val failureMessage : LiveData<String?> get() = _failureMessage



    private val _counter = mutableIntStateOf(1)
    val counter :State<Int> get() = _counter



    fun clearCounter(){
        _counter.intValue = 1
    }
    fun incrementCounter(){
        _counter.intValue++
    }

    fun decrementCounter() {
        if(counter.value >1){
        _counter.intValue -=1
        }
    }




    fun clearToast(){
        _successMessage.value =null
        _failureMessage.value =null
    }
    fun addToCart(payload: AddToCartRequest, callBack: (Boolean) -> Unit) {
        _successMessage.value = null
        _failureMessage.value = null
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.addToCart(payload)
            }
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    _cartList.postValue(listOf(it))
                    _successMessage.value = "SuccessFully Added to Cart"
                    callBack(true)

                }
            } else {
                _failureMessage.value = "Failed to add to cart: ${result.exceptionOrNull()?.message}"
                callBack(false)  // Notify caller that operation failed
            }
        }
    }
    fun deleteCart(id: Int) {
        _successMessage.value = null
        _failureMessage.value = null
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.deleteCart(id)
            }
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    _cartList.postValue(listOf(it))
                    _successMessage.value = "SuccessFully Added to Cart"


                }
            } else {
                _failureMessage.value =
                    "Failed to add to cart: ${result.exceptionOrNull()?.message}"

            }
        }
    }

    fun updateCart(id:Int , payload:UpdateCartRequest,callBack: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repository.updateCartRequest(id,payload)
            if(result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    _cartList.postValue(listOf(it))
                    _successMessage.value = "SuccessFully Updated cart!"
                    callBack(true)
                }
            }
            else {
                val exception = result.exceptionOrNull()
                _failureMessage.value = "Failed to add to cart: ${exception?.message}"
                callBack(false)
            }
        }
    }


}