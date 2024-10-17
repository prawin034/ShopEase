package com.example.shopease.feature_common.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor
import com.example.shopease.feature_common.utils.productCategoryUrl
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductCategoryCard(productCategory: AllProductCategory) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .clickable {

            }
            .width(170.dp)
            .height(60.dp)
            .padding(horizontal = 6.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(0.3.dp, color = Color(ShopAppConstants.CardBorderColor))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

                AsyncImageComponent(imageUrl = productCategoryUrl(productCategory.slug), modifier = Modifier.width(60.dp).height(50.dp), color = generateRandomColor())
                Text(
                    text = productCategory.slug?.capitalize(Locale.ROOT) ?: "",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    color = Color(ShopAppConstants.AppPrimaryTextColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )

        }
    }






}


