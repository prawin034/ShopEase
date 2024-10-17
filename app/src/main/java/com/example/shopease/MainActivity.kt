package com.example.shopease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.shopease.feature_admin.data.remote.ApiRepository
import com.example.shopease.feature_admin.ui.navigation.App
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModelFactory
import com.example.shopease.ui.theme.ShopEaseTheme

class MainActivity : ComponentActivity() {

       private val commonViewModel by viewModels<CommonViewModel>()
        private lateinit var homeScreenViewModel : HomeScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //api Repository instance
        val apiRepository : ApiRepository = ApiRepository()

        //viewModelFactory
        val factory = HomeScreenViewModelFactory(apiRepository)

        //create viewModel

        homeScreenViewModel = ViewModelProvider(owner = this, factory = factory)[HomeScreenViewModel::class.java]



        setContent {
            ShopEaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                   App(commonViewModel, homeScreenViewModel)
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//
//    val commonViewModel :CommonViewModel = viewModel()
//    ShopEaseTheme {
//        App(commonViewModel)
//    }
//}