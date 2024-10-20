package com.example.shopease.feature_admin.ui.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.ui.screens.all.cart.CartScreen
import com.example.shopease.feature_admin.ui.screens.all.home.HomeScreen
import com.example.shopease.feature_admin.ui.screens.all.profile.ProfileScreen
import com.example.shopease.feature_admin.ui.screens.all.search.SearchScreen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.CustomBottomBar
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.mapIconName


/* This screen acts as the main screen of whole application basically the entry point gateway for the application
* */


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminScreen(
    navController: NavHostController,
    commonViewModel: CommonViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {

    val bottomList = listOf<String>("Home","Cart","Search","Profile")
    val activeTab by commonViewModel.activeTabIndex.observeAsState()
    AppScaffold(
        topAppBar = {},
        bottomAppBar = {
                 CustomBottomBar {
                    bottomList.forEachIndexed { index, iconName ->
                        IconButton(
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
        floatingButton = { /*TODO*/ }
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
                1 -> CartScreen()
                2 -> SearchScreen(navController,homeScreenViewModel)
                3 -> ProfileScreen()
            }


        } // -> end of column



    }




} // -> End
