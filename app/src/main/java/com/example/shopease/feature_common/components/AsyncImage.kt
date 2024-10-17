package com.example.shopease.feature_common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun AsyncImageComponent(
    imageUrl: String,
    width: Int,
    height: Int,
    color: Color = Color.LightGray,
    badgeAvailable : Boolean = false,
    badgeName :String = "",

) {
    val painter = rememberAsyncImagePainter(imageUrl)
    val painterState = painter.state
    Box() {

        Image(
            painter = painter,
            contentDescription = "image",
            modifier = Modifier
                .width(width.dp)
                .height(height.dp)
                .background(color = color, shape = RoundedCornerShape(12.dp))

        )

//        if(painterState is AsyncImagePainter.State.Loading) {
//            CircularProgressIndicator()
//        }
//        else if (painterState is AsyncImagePainter.State.Error) {
//            Text(text = "Error..loading", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color.Red)
//        }

        if(badgeAvailable) {
           BadgeLabel(text = badgeName)



        }

    }
}