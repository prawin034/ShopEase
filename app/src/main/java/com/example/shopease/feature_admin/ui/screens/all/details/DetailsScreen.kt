package com.example.shopease.feature_admin.ui.screens.all.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.BadgeLabel
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.DescriptionText
import com.example.shopease.feature_common.components.HeaderText
import com.example.shopease.feature_common.components.MoreIconButton
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.components.SeeAllText
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    id: Int?
) {
    
    
    val product = homeScreenViewModel.getSingleProduct
    
    LaunchedEffect(Unit){
        
        if(id !=null){
            homeScreenViewModel.getSingleProduct(id)
        }
        
    }
    
    
    AppScaffold(
        topAppBar = {
           CustomTopAppBar(
               navigationIcon = {
                      BackIconButton {
                          
                      }          
               },
               titleContent = {
                       SectionTitleTxt(title = "Details Products")
               },
               actions = {
                   MoreIconButton {

                   }
               }
           )        
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ }) 
    {
        
        
        if(product.isNotEmpty()) {
        DetailProduct(navController,homeScreenViewModel,product)
        }
        else {
            LinearProgressIndicator()
        }
        

        
    }
    
}





@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailProduct(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    product: List<Product>
) {

    val scope = rememberCoroutineScope()
    val showFullText = remember { mutableStateOf(true) }
    LazyColumn {
        itemsIndexed(product){index: Int, item: Product ->
            val pager = rememberPagerState(initialPage = 0, pageCount = { item.images.size })
            
            
            // load images
            Column {

                HorizontalPager(
                    modifier = Modifier,
                    state = pager,
                    pageSpacing = 0.dp,
                    userScrollEnabled = true,
                    reverseLayout = false,
                    contentPadding = PaddingValues(0.dp),

                    pageSize = PageSize.Fill,
                    flingBehavior = PagerDefaults.flingBehavior(state = pager),
                    key = {item.images[it]},
                    pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                        state = pager,
                        orientation = Orientation.Horizontal
                    ),
                ) { index ->

                    val showImage = item.images[index]
                    Box(contentAlignment = Alignment.TopEnd) {
                        AsyncImageComponent(
                            imageUrl = showImage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            shape = RoundedCornerShape(0.dp),
                            color = Color(ShopAppConstants.cardLightColor)
                        )


                        Box(contentAlignment = Alignment.TopEnd){
                            Column(modifier = Modifier.padding(4.dp)) {
                                item.images.forEachIndexed { index, s ->

                                    AsyncImageComponent(
                                        imageUrl = s,
                                        modifier = Modifier
                                            .clickable {

                                                scope.launch {

                                                    pager.animateScrollToPage(page = index)
                                                }
                                            }
                                            .width(55.dp)
                                            .height(55.dp)
                                            .padding(bottom = 7.dp),
                                        color = Color(ShopAppConstants.AppPrimaryColor)
                                    )


                                }


                            }
                        }



                    }




                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item.images.forEachIndexed { SubIndex, item ->

                        Canvas(modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(10.dp)) {
                            drawCircle(
                                color = if(SubIndex == pager.currentPage) Color.Red else Color.LightGray,

                                )
                        }
                    }
                }


            }

            //cotent
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                BadgeLabel(text = item.availabilityStatus)

                Spacer(modifier = Modifier.height(10.dp))

                HeaderText(title = item.title, showFullTextBoolean = showFullText)

                Spacer(modifier = Modifier.height(10.dp))

                DescriptionText(title = item.description, fontSize = 13, letterSpacing = 4)

                Spacer(modifier = Modifier.height(10.dp))

                CommonRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    PriceText(fontSize = 13, title = "$${item.price}", color = Color(ShopAppConstants.AppPrimaryColor)) {

                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SeeAllText(title = "Category : ") {

                        }
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        BadgeLabel(text = item.category)
                    }



                }
                Spacer(modifier = Modifier.height(6.dp))

                SeeAllText(title = item.returnPolicy) {
                    
                }




            }


            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color(ShopAppConstants.dividerColor))


            // Reviews
            
            
            
            
            
        }
    }
    
    
    
    
    
    
}