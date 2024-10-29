package com.example.shopease.feature_admin.ui.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopease.feature_admin.data.model.AddToCarProduct
import com.example.shopease.feature_admin.data.model.AddToCartRequest
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



    private val _addCartItemsList = mutableSetOf<AddToCarProduct>()
    val addToCartItems: Set<AddToCarProduct> get() = _addCartItemsList

    fun addItem(item: AddToCarProduct) {
        _addCartItemsList.add(item) // This will automatically trigger recomposition in Compose
    }


    private val _confirmationAlert = MutableLiveData<Boolean>(false)
    val confirmationAlert : LiveData<Boolean> get() = _confirmationAlert


    fun updateConfirmationAlert(value: Boolean) {
        _confirmationAlert.value = value
    }


}