package com.example.shopease.feature_admin.ui.screens.all.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.CartIconBtn
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.utils.ShopAppConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel,commonViewModel: CommonViewModel) {


    AppScaffold(
        topAppBar = {
                CustomTopAppBar(
                    navigationIcon = {
                          BackIconButton(
                              color = Color.Black
                          ) {
                            commonViewModel.changeActiveTab(0)
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