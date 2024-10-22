package com.example.shopease.feature_common.components

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    colors :TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        scrolledContainerColor = Color.LightGray,
        containerColor = Color.White,

    ) ,
    navigationIcon : @Composable () -> Unit,
    titleContent : @Composable () -> Unit,
    actions : @Composable () -> Unit,

){
    
    TopAppBar(
        modifier = Modifier.shadow(2.dp, ambientColor = Color.Black),
        title = {titleContent()},
        navigationIcon = { navigationIcon()},
        actions = {
            actions()
        },
        colors = colors,
    )
    
    
    
} // end -> TopAppBar