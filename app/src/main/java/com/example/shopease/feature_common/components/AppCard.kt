package com.example.shopease.feature_common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopease.feature_common.utils.ShopAppConstants


@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    colors :CardColors = CardDefaults.cardColors(containerColor = Color(ShopAppConstants.AppPrimaryColor)),
    paddingValues: PaddingValues = PaddingValues(0.dp),
    enableDefaultPadding : Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(topStart = 12.dp , topEnd = 12.dp, bottomStart = 12.dp, bottomEnd = 12.dp),
    content: @Composable () -> Unit,
)
{
    val padding = if(enableDefaultPadding) PaddingValues(9.dp, 4.dp, 9.dp, 4.dp) else paddingValues
    Card(
        colors = colors,
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
            .shadow(3.dp, shape = shape, ambientColor = Color.Black),

        shape = shape
    ) {
        content()
    }
}