package com.example.shopease.feature_common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HorizontalDistribute
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
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
) {
    var isPressed by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isPressed) Color.LightGray else Color.White // Change color on press

        ),
        modifier = Modifier
            .animateContentSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        // Await the press and then invoke the onClick action
                        tryAwaitRelease() // This will wait until the user releases the press
                        isPressed = false
                    },
                    onTap = {
                        onClick.invoke()
                    }
                )
            }
            .width(60.dp)
            .height(60.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(0.3.dp, color = Color(ShopAppConstants.CardBorderColor))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.HorizontalDistribute,
                contentDescription = "App menu btn",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
