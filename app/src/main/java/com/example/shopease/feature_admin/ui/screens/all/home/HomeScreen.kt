package com.example.shopease.feature_admin.ui.screens.all.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.ui.navigation.Screen
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController,homeScreenViewModel: HomeScreenViewModel){

    // 1 -> Get All products Category

    val getAllProductCategory = homeScreenViewModel.getAllProductCategory
    val productBasedOnHotSales = homeScreenViewModel.getProductBasedOnHotSalesCategory
    val productBasedOnBeauty = homeScreenViewModel.getProductBasedOnBeautyCategory
    val productBasedOnFurniture = homeScreenViewModel.getProductBasedOnFurnitureCategory
    val productBasedOnGrocery = homeScreenViewModel.getProductBasedOnGroceriesCategory
    val productBasedOnWomenDress = homeScreenViewModel.getProductBasedOnWomens_dressesCategory

    LaunchedEffect(Unit) {
        homeScreenViewModel.getAllProductsCategory()
        homeScreenViewModel.getProductBasedOnCategory("laptops")
        homeScreenViewModel.getProductBasedOnBeautyCategory("beauty")
        homeScreenViewModel.getProductBasedOnFurnitureCategory("furniture")
        homeScreenViewModel.getProductBasedOnGroceryCategory("groceries")
        homeScreenViewModel.getProductBasedOnWomenDressCategory("womens-dresses")

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
                .verticalScroll(rememberScrollState())
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
            Column {
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
                // 3 -> Hot Sales
                if(productBasedOnHotSales.isNotEmpty()) {
                    CommonCategory(productBasedOnHotSales[0].products,navController)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))





            Column {
                CommonRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    SectionTitleTxt(title = "Beauty")

                    SeeAllText(title = "see all") {
                    }
                }

                //4 -> Beauty
                if(productBasedOnBeauty.isNotEmpty()) {
                    CommonCategory(productBasedOnBeauty[0].products, navController)
                }
            }


            Spacer(modifier = Modifier.height(15.dp))


            //5 -> furniture
            Column {
                CommonRow(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    SectionTitleTxt(title = "Furniture")

                    SeeAllText(title = "see all") {
                    }
                }
                //4 -> Beauty
                if(productBasedOnFurniture.isNotEmpty()) {
                    CommonCategory(productBasedOnFurniture[0].products,navController)
                }
            }




            Spacer(modifier = Modifier.height(15.dp))


            Column {
                CommonRow(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    SectionTitleTxt(title = "Grocery")

                    SeeAllText(title = "see all") {
                    }
                }
                //4 -> Beauty
                if(productBasedOnGrocery.isNotEmpty()) {
                    CommonCategory(productBasedOnGrocery[0].products,navController)
                }
            }


            Spacer(modifier = Modifier.height(15.dp))


            Column {
                CommonRow(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    SectionTitleTxt(title = "Women-dress")

                    SeeAllText(title = "see all") {
                    }
                }
                //4 -> Beauty
                if(productBasedOnWomenDress.isNotEmpty()) {
                    CommonCategory(productBasedOnWomenDress[0].products,navController)
                }
            }



        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommonCategory(productBasedOnCategory: List<Product>,navController: NavController) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 10.dp)){
        itemsIndexed(productBasedOnCategory){index: Int, item: Product ->

            Column(modifier = Modifier.clickable {
                navController.navigate("${Screen.DetailsScreen.route}/${item.id}")
            }.padding(horizontal = 10.dp)) {
//0xFFF7F7FF
                AsyncImageComponent(imageUrl = item.thumbnail, modifier = Modifier.width(200.dp).height(200.dp), color = generateRandomColor(),badgeAvailable = true, badgeName = item.availabilityStatus)
                CommonRow(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    HeaderText(title = item.title, modifier = Modifier
                        .width(150.dp)
                        .padding(bottom = 3.dp, top = 5.dp))
                    PriceText(title = item.price.toString()) {
                    }
                }

                DescriptionText(title = item.description, modifier = Modifier.width(200.dp))
            }

        }
    }
}