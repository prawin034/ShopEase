package com.example.shopease.feature_common.animations

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopease.feature_common.utils.ShopAppConstants


@Composable
fun MarqueTab(list:List<String>) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(ShopAppConstants.AppPrimaryColor))
            .padding(10.dp)
            .basicMarquee(
                iterations = Int.MAX_VALUE,
                animationMode = MarqueeAnimationMode.Immediately,
                initialDelayMillis = 2,
                repeatDelayMillis = 1,
                spacing = MarqueeSpacing(5.dp),
                velocity = 80.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        list.forEachIndexed { index, s ->
            Text(text = s.plus("  *  "), fontSize = 13.sp, color = Color.White, modifier = Modifier.padding(horizontal = 0.dp))
        }

    }



}