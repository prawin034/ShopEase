package com.example.shopease.feature_admin.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.shopease.feature_admin.data.model.CardList
import com.example.shopease.feature_common.utils.ShopAppConstants

class CardViewModel : ViewModel() {



    private val _cardList = mutableStateListOf<CardList>()
    val cardList : List<CardList> get() = _cardList


    private val _successMsg = mutableStateOf<String?>(null)
    val successMsg  : State<String?> get() = _successMsg

    private val _failureMsg = mutableStateOf<String?>(null)
    val failureMsg  : State<String?> get() = _failureMsg


    private val _selectedColorName = mutableStateOf<String>("Dark-Purple")
    val selectedColorName : State<String> get() = _selectedColorName

    fun addToCard(item : CardList) {
        _cardList.add(item)
        _successMsg.value = "SuccessFully Added !"
    }



    fun updateSelectedColor(colorName:String){
        _selectedColorName.value = colorName
    }




}