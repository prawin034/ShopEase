package com.example.shopease.feature_common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlignHorizontalCenter
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopease.feature_common.utils.ShopAppConstants


/* App Buttons

   This page contains app reusable buttons
 */


@Composable
fun AppMenuBtn(
    onClick : () -> Unit
){
    
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(ShopAppConstants.AppPrimaryColor)
        ),
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "App menu btn",
            tint = Color(ShopAppConstants.AppIconColor),
            modifier = Modifier.size(23.dp)
        )
    }
}


@Composable
fun FilterBtn(
    onClick: () -> Unit
){

    IconButton(
        onClick = {
            onClick.invoke()
        }
    ) {
        Icon(
            imageVector = Icons.Default.AlignHorizontalCenter,
            contentDescription = "App menu btn",
            tint = Color.Black,
            modifier = Modifier.size(26.dp)
        )
    }
}