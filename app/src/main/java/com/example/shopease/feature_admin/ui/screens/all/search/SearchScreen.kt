package com.example.shopease.feature_admin.ui.screens.all.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.CartIconBtn
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.utils.ShopAppConstants


@Composable
fun SearchScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel) {



    AppScaffold(
        topAppBar = {
                CustomTopAppBar(
                    containerColor = Color(ShopAppConstants.AppPrimaryColor),
                    navigationIcon = {
                          BackIconButton(
                              color = Color.White
                          ) {
                              navController.popBackStack()
                          }
                    },
                    titleContent = {
                       SectionTitleTxt(title = "Search", color = Color.White)
                    },
                    actions = {

                        CartIconBtn {

                        }

                    }
                )
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    {

    }

}