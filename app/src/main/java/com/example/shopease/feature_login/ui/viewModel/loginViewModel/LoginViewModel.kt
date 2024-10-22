package com.example.shopease.feature_login.ui.viewModel.loginViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.remote.ApiRepository
import com.example.shopease.feature_login.dataStore.saveToken
import com.example.shopease.feature_login.model.loginRequest
import com.example.shopease.feature_login.model.loginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: ApiRepository) : ViewModel() {


    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String> get() = _userName

    fun isChangeEmail(value:String) {
        _userName.value = value
    }


    private val _password = MutableLiveData<String>()
    val password : LiveData<String> get() = _password

    fun  changePassword(value: String) {
        _password.value = value
    }


    private val _loginResponse = MutableLiveData<loginResponse?>()
    val loginResponse : LiveData<loginResponse?> get() = _loginResponse


    private val _successMessage = MutableLiveData<String?>(null)
    val successMessage : LiveData<String?> get() = _successMessage


    private val _failureMessage = MutableLiveData<String?>(null)
    val failureMessage : LiveData<String?> get() = _failureMessage

    fun login(context: Context,payload:loginRequest,callBack : (Boolean) -> Unit ) {
        _successMessage.value = null
        _failureMessage.value =null
        viewModelScope.launch {
            val result = repository.login(payload)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _successMessage.value = "Successfully loaded response"
                    callBack(true)
                    _loginResponse.postValue(response)
                    saveToken(context,response.accessToken)
                }
                else {
                    _failureMessage.value = "Failed to load: response is null"
                }
            }
            else {
                val exception = result.exceptionOrNull()
                _failureMessage.value = "Failed: ${exception?.message ?: "Unknown error"}"
            }
        }
    }

}