package com.example.shopease.feature_common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun AsyncImageComponent(
    imageUrl: String,

    color: Color = Color.LightGray,
    badgeAvailable : Boolean = false,
    badgeName :String = "",
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    modifier: Modifier = Modifier

) {
    val painter = rememberAsyncImagePainter(imageUrl, filterQuality = FilterQuality.Low)
    val painterState = painter.state
    Box() {

        Image(

            painter = painter,
            contentDescription = "image",
            modifier = modifier
                .background(color = color, shape )

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