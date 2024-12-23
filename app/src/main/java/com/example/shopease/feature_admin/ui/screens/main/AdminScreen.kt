package com.example.shopease.feature_admin.ui.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.ui.screens.all.cart.CartScreen
import com.example.shopease.feature_admin.ui.screens.all.categories.CategoriesScreen
import com.example.shopease.feature_admin.ui.screens.all.home.HomeScreen
import com.example.shopease.feature_admin.ui.screens.all.profile.ProfileScreen
import com.example.shopease.feature_admin.ui.screens.all.search.SearchScreen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.cart.CartViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_admin.ui.viewModel.seach.SearchViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.CustomBottomBar
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.mapIconName
import com.example.shopease.feature_login.ui.viewModel.loginViewModel.LoginViewModel


/* This screen acts as the main screen of whole application basically the entry point gateway for the application
* */


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminScreen(
    navController: NavHostController,
    commonViewModel: CommonViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    loginViewModel: LoginViewModel,
    cartViewModel: CartViewModel,
    searchViewModel: SearchViewModel
) {

    val bottomList = listOf<String>("Home","Search", "Categories","Profile","Cart")
    val activeTab by commonViewModel.activeTabIndex.observeAsState()
    AppScaffold(
        topAppBar = {},
        bottomAppBar = {
                 CustomBottomBar {
                    bottomList.forEachIndexed { index, iconName ->
                        IconButton(
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = if(activeTab == index) Color.Black else Color.Transparent
                            ),
                            onClick = {
                                commonViewModel.changeActiveTab(index)
                            }
                        ) {
                            Icon(
                                imageVector = mapIconName(iconName),
                                contentDescription = "",
                                tint = if(activeTab  == index)  Color(ShopAppConstants.AppIconColor) else Color(ShopAppConstants.AppSecondaryTextColor)
                            )
                        }
                    }

                 }
        },
        floatingButton = { /*TODO*/ },
        snackbarHost = {}
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            when(activeTab) {
                0 -> HomeScreen(navController,homeScreenViewModel)
                1 -> SearchScreen(navController,homeScreenViewModel,commonViewModel,searchViewModel)
                2 -> CategoriesScreen(navController,homeScreenViewModel,searchViewModel,commonViewModel)
                3 -> ProfileScreen(navController,loginViewModel)
                4 -> CartScreen(navController , cartViewModel,commonViewModel)
            }


        } // -> end of column



    }




} // -> End
