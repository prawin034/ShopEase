package com.example.shopease.feature_login.ui.viewModel.registrationViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.remote.ApiRepository
import com.example.shopease.feature_login.dataStore.saveToken
import com.example.shopease.feature_login.model.RegisterRequest
import com.example.shopease.feature_login.model.loginRequest
import kotlinx.coroutines.launch

class RegistrationViewModel(private val repository: ApiRepository) :ViewModel() {


    private val _firstName = MutableLiveData<String>()
    val firstName : LiveData<String> get() = _firstName

    fun updateFirstName(value:String) {
        _firstName.value = value
    }



    private val _lastName = MutableLiveData<String>()
    val lastName : LiveData<String> get() = _lastName

    fun updateLastName(value:String) {
        _lastName.value = value
    }



    private val _email = MutableLiveData<String>()
    val email : LiveData<String> get() = _email

    fun updateEmail(value:String) {
        _email.value = value
    }


    private val _password = MutableLiveData<String>()
    val password : LiveData<String> get() = _password

    fun updatePassword(value:String) {
        _password.value = value
    }


    private val _successMessage = MutableLiveData<String?>(null)
    val successMessage : LiveData<String?> get() = _successMessage


    private val _failureMessage = MutableLiveData<String?>(null)
    val failureMessage : LiveData<String?> get() = _failureMessage

    fun register(context: Context, payload: RegisterRequest, callBack : (Boolean) -> Unit ) {
        _successMessage.value = null
        _failureMessage.value =null
        viewModelScope.launch {
            val result = repository.register(payload)
            if(result.isSuccess) {
                val response = result.getOrNull()
                if(response !=null) {
                    _successMessage.value = "Registered SuccessFully"
                    callBack(true)
                }
                else {
                    _failureMessage.value = "Failed to Register: response is null"
                }
            }
            else {
                val exception = result.exceptionOrNull()
                _failureMessage.value = "Failed: ${exception?.message ?: "Unknown error"}"
            }
        }
    }



}