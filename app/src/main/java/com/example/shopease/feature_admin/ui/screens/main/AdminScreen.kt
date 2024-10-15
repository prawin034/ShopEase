package com.example.shopease.feature_admin.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shopease.feature_common.components.AppMenuBtn
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.CustomTopAppBar


/* This screen acts as the main screen of whole application basically the entry point gateway for the application
* */

@Composable
fun AdminScreen() {

    AppScaffold(
        topAppBar = {
             CustomTopAppBar(
                 navigationIcon = {
                       AppMenuBtn {

                       }
                 },
                 titleContent = {}
             ) {


             }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


        } // -> end of column


    }




} // -> End
