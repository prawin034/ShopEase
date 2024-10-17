package com.example.shopease.feature_common.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopease.feature_common.utils.ShopAppConstants


@Composable
fun CustomBottomBar(
    content : @Composable () -> Unit
){


    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .clip(shape = RoundedCornerShape(17.dp)),
        containerColor = Color(ShopAppConstants.AppBottomBarContainerColor),
        contentColor = Color(ShopAppConstants.AppIconColor),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
            content()
            }
        }
    )

}