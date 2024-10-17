package com.example.shopease.feature_admin.ui.viewModel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopease.feature_admin.data.remote.ApiRepository

class HomeScreenViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
             @Suppress("UNCHECKED_CAST")
             return  HomeScreenViewModel(repository) as T
        }

        throw  IllegalArgumentException("Unknown viewModel class")

    }
}