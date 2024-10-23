package com.example.shopease.feature_common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_login.dataStore.getTheme

/*  This App Scaffold works as a reusable component layout throughout the application.
*
* */



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold(
    topAppBar : @Composable () -> Unit,
    bottomAppBar : @Composable () -> Unit,
    floatingButton : @Composable () -> Unit,
    floatingPosition : FabPosition =  FabPosition.Center,
    contentColor : Color = Color.White,
    containerColor: Color =  Color.White,
    content : @Composable (PaddingValues) -> Unit

){
    val context = LocalContext.current
    val commonViewModel :CommonViewModel = viewModel()
    val theme by commonViewModel.isDarkTheme.observeAsState(initial = getTheme(context))
   
    val containerColorUpdate =  if(theme) Color.Black else containerColor
    val contentColorUpdate  =  if(theme) Color.White else  contentColor
    Scaffold(
        topBar = {
                 topAppBar()
        },
        bottomBar = {
                 bottomAppBar()
        },
        floatingActionButton = {
                 floatingButton()
        },
        floatingActionButtonPosition = floatingPosition,
        contentColor = contentColorUpdate,
        containerColor = containerColorUpdate,
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets(0.dp)),
                color = containerColorUpdate
            ) {
                content(it)

            }
        }
    )


} // -> end of scaffold