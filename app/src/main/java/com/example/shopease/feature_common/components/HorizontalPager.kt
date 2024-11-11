package com.example.shopease.feature_common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_common.animations.shimmerEffect
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor
import kotlinx.coroutines.launch


@SuppressLint("NewApi")
@Composable
fun ShopEasePager(product: List<Product>?, navController: NavController)
{

    val scope = rememberCoroutineScope()
    LazyColumn(modifier = Modifier.height(400.dp).background(generateRandomColor()),
        userScrollEnabled = false
    ) {
        itemsIndexed(product ?: emptyList()) { index: Int, item ->
            
            val pagerState = rememberPagerState(initialPage = 0, pageCount = {
                product?.size ?:0
            })


            Column {

                HorizontalPager(
                    state = pagerState,
                    pageSpacing = 0.dp,
                    userScrollEnabled = true,
                    reverseLayout = false,
                    contentPadding = PaddingValues(0.dp),
                    pageSize = PageSize.Fill,
                    flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                    key = {
                            pageIndex -> product!![pageIndex].id
                    },
                    pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                        state = pagerState,
                        orientation = Orientation.Horizontal
                    )
                ) {index ->
                    val productItem = product!![index]
                    val image =  productItem.thumbnail



                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                        AsyncImageComponent(
                            imageUrl = image,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            shape = RoundedCornerShape(0.dp),
                            color = Color.Transparent,
                            badgeAvailable = true,
                            badgeName = "HotSales",
                            contentAlignment = Alignment.BottomEnd
                        )



                    }



                }











            }


            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = -20.dp)
                .padding(bottom = 12.dp)
                .padding(horizontal = 5.dp), horizontalArrangement = Arrangement.Center)
            {


                product?.forEachIndexed { thumbNailIndex, thumNailProduct ->
                    Canvas(modifier = Modifier

                        .clickable {

                            scope.launch {

                                pagerState.animateScrollToPage(thumbNailIndex)
                            }


                        }
                        .padding(horizontal = 5.dp)
                        .clip(CircleShape)
                        .size(10.dp), onDraw =  {
                        drawCircle(color = if(thumbNailIndex == pagerState.currentPage) Color.Red else Color.White )
                    })
                }
            }
            
            
            
            
        }
    }
    
    
    
    
    
    
    

}


@Composable
fun loading(modifier: Modifier = Modifier.fillMaxWidth(),height : Dp =  400.dp){
    Box(modifier = Modifier.fillMaxWidth().height(height).shimmerEffect(
        RoundedCornerShape(0.dp)
    ))
}