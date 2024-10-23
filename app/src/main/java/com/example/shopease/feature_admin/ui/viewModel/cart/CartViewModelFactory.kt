package com.example.shopease.feature_admin.ui.viewModel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopease.feature_admin.data.remote.ApiRepository

@Suppress("UNCHECKED_CAST")
class CartViewModelFactory(private val repository: ApiRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return  CartViewModel(repository) as T
        }

        throw  IllegalArgumentException("Unknown viewModel")
    }
}