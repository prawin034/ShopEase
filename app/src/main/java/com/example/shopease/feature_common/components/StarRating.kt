package com.example.shopease.feature_common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(rating:Double) {
    val maxStars :Int = 5
    CommonRow {
        for(star in 1..maxStars.toInt()) {


            val icon = when {
                rating >= star -> Icons.Default.Star
                rating >= star - 0.5 -> Icons.Default.StarHalf
                else -> Icons.Default.StarOutline
            }

            Icon(
                imageVector = icon,
                contentDescription ="Star",
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(23.dp)
            )
        }
    }

}