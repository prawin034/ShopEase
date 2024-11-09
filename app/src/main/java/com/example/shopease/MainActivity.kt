package com.example.shopease

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.shopease.feature_admin.navigation.App
import com.example.shopease.feature_admin.ui.viewModel.CardViewModel
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.cart.CartViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_admin.ui.viewModel.seach.SearchViewModel
import com.example.shopease.feature_login.dataStore.getTheme
import com.example.shopease.feature_login.ui.viewModel.loginViewModel.LoginViewModel
import com.example.shopease.feature_login.ui.viewModel.registrationViewModel.RegistrationViewModel
import com.example.shopease.ui.theme.ShopEaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val commonViewModel by viewModels<CommonViewModel>()
    private val cardViewModel by viewModels<CardViewModel>()
    private  val  homeScreenViewModel by viewModels<HomeScreenViewModel>()
    private  val  loginViewModel by viewModels<LoginViewModel>()
    private  val  registrationViewModel by viewModels<RegistrationViewModel>()
    private  val  cartViewModel by viewModels<CartViewModel>()
    private  val  searchViewModel by viewModels<SearchViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       WindowCompat.setDecorFitsSystemWindows(window,false)
      // enableEdgeToEdge()
        setContent {
            var isDarkThemes by remember { mutableStateOf(getTheme(applicationContext)) }
            val theme by commonViewModel.isDarkTheme.observeAsState(isDarkThemes)
            ShopEaseTheme(
                darkTheme = theme,
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                   App(
                       commonViewModel,
                       homeScreenViewModel,
                       loginViewModel,
                       registrationViewModel,
                       cartViewModel,
                       searchViewModel,
                       cardViewModel
                   )
                }
            }
        }
    }
}
