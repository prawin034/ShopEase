package com.example.shopease.feature_login.ui.viewModel.registrationViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopease.feature_admin.data.remote.ApiRepository

@Suppress("UNCHECKED_CAST")
class RegistrationViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return  RegistrationViewModel(repository) as T
        }

        throw  IllegalArgumentException("Unknown viewModel class")
    }
}