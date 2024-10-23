package com.example.shopease.feature_admin.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopease.feature_login.dataStore.getTheme
import com.example.shopease.feature_login.dataStore.saveTheme

class CommonViewModel : ViewModel() {



    private val _activeTabIndex  = MutableLiveData<Int>(0)
    val activeTabIndex  :LiveData<Int> get() = _activeTabIndex


    // -> This function is responsible for changing activeTab

    fun changeActiveTab(index:Int) {
        _activeTabIndex.value = index
    }

    private val _isDarkTheme = MutableLiveData<Boolean>()
    val isDarkTheme : LiveData<Boolean> get() = _isDarkTheme


    fun updateTheme(value:Boolean,context: Context) {
        saveTheme(context,value)
        _isDarkTheme.value = value

    }



}