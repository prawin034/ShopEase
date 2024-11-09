package com.example.shopease.feature_admin.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopease.feature_admin.data.model.BinLookUpList
import com.example.shopease.feature_admin.data.model.CardList
import com.example.shopease.feature_admin.data.remote.ApiRepository
import com.example.shopease.feature_common.utils.ShopAppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {



    private val _cardList = mutableStateListOf<CardList>()
    val cardList : List<CardList> get() = _cardList



    private val _successMsg = mutableStateOf<String?>(null)
    val successMsg  : State<String?> get() = _successMsg

    private val _failureMsg = mutableStateOf<String?>(null)
    val failureMsg  : State<String?> get() = _failureMsg


    private val _cardId = mutableStateOf<String>("")
    val cardId : State<String> get() = _cardId


    fun addCardId(id :String) {
        _cardId.value = id
    }


    fun addToCard(item : CardList) :String {
        _cardList.add(item)
        _successMsg.value = "SuccessFully Added !"
        return  item.id
    }


    fun updateCardColorById(id:String,cardColor:Color) {

        _cardList.find { it.id == id }.let { card ->
            val cardIndex = _cardList.indexOf(card)
            _cardList[cardIndex] = card!!.copy(cardColor = cardColor)
        }
    }


    //1 - card Details



    private val _cardNumber = MutableLiveData<Int>(0)
    val cardNumber : LiveData<Int> get() = _cardNumber


    fun changeCardNum(value:Int) {
        _cardNumber.value = value
    }










    // 2 Step 2 Card Style

    private val _selectedColorName = mutableStateOf<String>("Dark-Purple")
    val selectedColorName : State<String> get() = _selectedColorName

    fun updateSelectedColor(colorName:String){
        _selectedColorName.value = colorName
    }



    private val _binlookUpList = MutableLiveData<List<BinLookUpList>>()
    val binlookupList : LiveData<List<BinLookUpList>> get() = _binlookUpList






    fun getBinList(cardnumber :Int) {
        viewModelScope.launch {
            val result = repository.binlookup(cardnumber)
            when {
                result.isSuccess -> {
                    val response = result.getOrNull()
                    response?.let {
                        _binlookUpList.postValue( listOf(it))
                    }
                }

                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    _failureMsg.value = "Failed: ${exception?.localizedMessage ?: "Unknown Error"}"
                }
            }
        }
    }
    fun clearbin(){
        _binlookUpList.value = emptyList()
    }
}