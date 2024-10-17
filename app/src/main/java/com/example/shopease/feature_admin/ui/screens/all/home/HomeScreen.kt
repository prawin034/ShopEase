package com.example.shopease.feature_admin.ui.screens.all.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.components.AppMenuBtn
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.DescriptionText
import com.example.shopease.feature_common.components.FilterBtn
import com.example.shopease.feature_common.components.HeaderText
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.ProductCategoryCard
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.components.SeeAllText
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor


@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel){

    // 1 -> Get All products Category

    val getAllProductCategory = homeScreenViewModel.getAllProductCategory
    val productBasedOnCategory = homeScreenViewModel.getProductBasedOnCategory


    LaunchedEffect(Unit) {
        homeScreenViewModel.getAllProductsCategory()
        homeScreenViewModel.getProductBasedOnCategory("laptops")
    }


    AppScaffold(
        topAppBar = {
                    CustomTopAppBar(
                        navigationIcon = {
                             AppMenuBtn {

                             }
                        },
                        titleContent = { /*TODO*/ }
                    ) {

                    }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // -> 1. First Row contains filter method and all category product cards

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row {
                    FilterBtn {

                    }
                }


                LazyRow(contentPadding = PaddingValues(horizontal = 10.dp)) {
                    itemsIndexed(getAllProductCategory){_: Int, item: AllProductCategory ->

                        ProductCategoryCard(productCategory = item)

                    }
                }

            }


            // 2 -> Horizontal Divider

            Spacer(modifier = Modifier.height(10.dp))


            Divider(thickness = 0.6.dp, color = Color(ShopAppConstants.dividerColor))

            Spacer(modifier = Modifier.height(10.dp))


            // 3 ->

            CommonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                SectionTitleTxt(title = "Hot Sales")

                SeeAllText(title = "see all") {

                }
                
                
                



            }


            if(productBasedOnCategory.isNotEmpty()) {
                    HotSalesCategory(productBasedOnCategory[0].products)
            }



        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotSalesCategory(productBasedOnCategory: List<Product>) {
    LazyRow(modifier = Modifier,contentPadding = PaddingValues(horizontal = 0.dp, vertical = 10.dp)){
        itemsIndexed(productBasedOnCategory){index: Int, item: Product ->

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
//0xFFF7F7FF
                AsyncImageComponent(imageUrl = item.thumbnail, width = 200, height = 200, color = generateRandomColor(),badgeAvailable = true, badgeName = item.availabilityStatus)
                CommonRow(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    HeaderText(title = item.title, modifier =Modifier.width(150.dp).padding(bottom = 3.dp, top = 5.dp))
                    PriceText(title = item.price.toString()) {

                    }

                }

                DescriptionText(title = item.description, modifier = Modifier.width(200.dp))
            }

        }
    }
}