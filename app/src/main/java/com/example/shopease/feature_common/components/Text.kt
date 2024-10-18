package com.example.shopease.feature_common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopease.feature_common.utils.ShopAppConstants


@Composable
fun SectionTitleTxt(
    modifier: Modifier =Modifier,
    title:String,
    fontSize: Int = 15,
    color : Color = Color(ShopAppConstants.AppPrimaryTextColor)
){

    Text(
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 3.sp,
        color = color,
        modifier = modifier
    )
}

@Composable
fun ProductText(
    title:String,
    fontSize: Int = 10,
    color : Color = Color.White,
    fontFamily: FontFamily = FontFamily.SansSerif
){

    Text(
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        letterSpacing = 2.sp,
        maxLines = 1,
        color = color
    )
}

@Composable
fun ProductTextHeader(
    title:String,
    fontSize: Int = 8,
    color : Color = Color.LightGray
){


    Text(
        text = title.uppercase(),
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.SansSerif,
        overflow = TextOverflow.Ellipsis,
        letterSpacing = 1.sp,
        color = color
    )
}





@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    title:String,
    fontSize: Int = 15,
    color : Color = Color(ShopAppConstants.AppPrimaryTextColor),
    showFullTextBoolean: MutableState<Boolean> = remember { mutableStateOf(false) }
){

    val displayText = if(showFullTextBoolean.value) title else title.take(10) + "..."
    Column {
        Text(
            text = displayText,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp,
            color = color,
            maxLines = 10,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
        )

        if(displayText.length > 10 && !showFullTextBoolean.value) {
            Text(
                text = "Show More",
                color = Color.Blue,
                fontSize = 10.sp,
                modifier = Modifier.clickable {
                    showFullTextBoolean.value = true
                }
            )
        }
        if (showFullTextBoolean.value) {
            Text(
                text = "Show Less",
                color = Color.Blue,
                fontSize = 10.sp,
                modifier = Modifier.clickable {
                    showFullTextBoolean.value = false
                }
            )
        }
    }

}
@Composable
fun DescriptionText(
    title:String,
    fontSize: Int = 10,
    letterSpacing: Int = 1,
    color : Color = Color(ShopAppConstants.AppSecondaryTextColor),
    modifier: Modifier = Modifier
){
    var showFullText by remember { mutableStateOf(false) }
    val displayText = if(showFullText) title else title.take(90) + "..."

    Column {
        Text(
            text = displayText,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = letterSpacing.sp,
            lineHeight = 14.sp,
            color = color,
            maxLines = 10,
            overflow = TextOverflow.Ellipsis,
            modifier= modifier
        )
    }

}


@Composable
fun SeeAllText(
    title: String,
    fontSize: Int = 10,
    color: Color = Color(ShopAppConstants.AppSecondaryTextColor),
    onClick : () -> Unit
){
    Text(
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 2.sp,
        color = color,
        modifier = Modifier.clickable {
            onClick.invoke()
        }
    )
}


@Composable
fun PriceText(
    title: String,
    fontSize: Int = 10,
    color: Color = Color(ShopAppConstants.AppSecondaryTextColor),
    onClick : () -> Unit
){
    Text(
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 2.sp,
        color = color,
        modifier = Modifier.clickable {
            onClick.invoke()
        }
    )
}



@Composable
fun BadgeLabel(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Color(ShopAppConstants.AppCardColor),
    textColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(bottomEnd = 4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}



@Composable
fun SpacerCommon(modifier: Modifier = Modifier.height(10.dp) ){
    Spacer(modifier = modifier )
}


@Composable
fun AppTxt(
    title:String,
    fontSize: Int = 10,
    color : Color = Color.Black,
    fontFamily: FontFamily = FontFamily.SansSerif
){

    Text(
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        letterSpacing = 2.sp,
        maxLines = 1,
        color = color
    )
}