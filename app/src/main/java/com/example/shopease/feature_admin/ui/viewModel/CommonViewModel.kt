package com.example.shopease.feature_admin.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommonViewModel : ViewModel() {



    private val _activeTabIndex  = MutableLiveData<Int>(0)
    val activeTabIndex  :LiveData<Int> get() = _activeTabIndex


    // -> This function is responsible for changing activeTab

    fun changeActiveTab(index:Int) {
        _activeTabIndex.value = index
    }
}