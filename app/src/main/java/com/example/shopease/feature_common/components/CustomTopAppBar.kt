package com.example.shopease.feature_common.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    containerColor : Color = Color.White,
    scrolledContainerColor : Color = Color.Blue.copy(alpha = 0.1f),
    navigationIconColor :Color = Color.Black,
    titleColor : Color = Color.Black,
    navigationIcon : @Composable () -> Unit,
    titleContent : @Composable () -> Unit,
    actions : @Composable () -> Unit,

){
    
    TopAppBar(
        title = {titleContent()},
        navigationIcon = { navigationIcon()},
        actions = {
            actions()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = scrolledContainerColor,
            navigationIconContentColor = navigationIconColor,
            titleContentColor = titleColor
        )
    )
    
    
    
} // end -> TopAppBar