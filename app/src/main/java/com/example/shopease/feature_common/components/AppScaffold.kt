package com.example.shopease.feature_common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
    containerColor: Color = Color.White,
    content : @Composable () -> Unit

){

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
        contentColor = contentColor,
        containerColor = containerColor,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize().padding(it),
                color = Color.White
            ) {
                content()

            }
        }
    )


} // -> end of scaffold