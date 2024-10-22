package com.example.shopease

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.example.shopease.feature_admin.data.remote.ApiRepository
import com.example.shopease.feature_admin.navigation.App
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModelFactory
import com.example.shopease.feature_login.ui.viewModel.loginViewModel.LoginViewModel
import com.example.shopease.feature_login.ui.viewModel.loginViewModel.LoginViewModelFactory
import com.example.shopease.ui.theme.ShopEaseTheme

class MainActivity : ComponentActivity() {

    private val commonViewModel by viewModels<CommonViewModel>()
    private lateinit var homeScreenViewModel : HomeScreenViewModel
    private lateinit var loginViewModel: LoginViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //api Repository instance
        val apiRepository  = ApiRepository(applicationContext)


        //viewModelFactory
        val factory = HomeScreenViewModelFactory(apiRepository)
        val loginFactory = LoginViewModelFactory(apiRepository)

        //create viewModel

        homeScreenViewModel = ViewModelProvider(owner = this, factory = factory)[HomeScreenViewModel::class.java]
        loginViewModel = ViewModelProvider(this, factory = loginFactory)[LoginViewModel::class.java]
       WindowCompat.setDecorFitsSystemWindows(window,false)
      // enableEdgeToEdge()
        setContent {
            ShopEaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                   App(commonViewModel, homeScreenViewModel,loginViewModel)
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