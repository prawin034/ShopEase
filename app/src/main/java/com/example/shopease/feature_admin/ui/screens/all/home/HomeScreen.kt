package com.example.shopease.feature_admin.ui.screens.all.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_admin.ui.screens.all.details.Avatar
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.animations.MarqueTab
import com.example.shopease.feature_common.animations.shimmerEffect
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.DescriptionText
import com.example.shopease.feature_common.components.HeaderText
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.ProductCategoryCard
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.components.SeeAllText
import com.example.shopease.feature_common.components.ShopEasePager
import com.example.shopease.feature_common.components.loading
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController,homeScreenViewModel: HomeScreenViewModel){

    // 1 -> Get All products Category

    val getAllProductCategory by  homeScreenViewModel.getAllProductCategory.observeAsState(initial = emptyList())
    val productBasedOnHotSales by homeScreenViewModel.getProductBasedOnHotSalesCategory.observeAsState(initial = emptyList())
    val productBasedOnBeauty by homeScreenViewModel.getProductBasedOnBeautyCategory.observeAsState(emptyList())
    val productBasedOnFurniture by  homeScreenViewModel.getProductBasedOnFurnitureCategory.observeAsState(initial = emptyList())
    val productBasedOnGrocery by  homeScreenViewModel.getProductBasedOnGroceriesCategory.observeAsState(initial = emptyList())
    val productBasedOnWomenDress by  homeScreenViewModel.getProductBasedOnWomens_dressesCategory.observeAsState(initial = emptyList())

    val isDataLoaed = rememberSaveable {
        mutableStateOf(false)
    }

//

    LaunchedEffect(Unit) {
        if(!isDataLoaed.value &&
            getAllProductCategory?.isEmpty() == true &&
            productBasedOnHotSales?.isEmpty() == true &&
            productBasedOnBeauty?.isEmpty() == true &&
            productBasedOnFurniture?.isEmpty() == true &&
            productBasedOnGrocery?.isEmpty() == true &&
            productBasedOnWomenDress?.isEmpty() == true
            )
        {
            homeScreenViewModel.getAllProductsCategory()
            homeScreenViewModel.getProductBasedOnCategory("laptops")
            homeScreenViewModel.getProductBasedOnBeautyCategory("beauty")
            homeScreenViewModel.getProductBasedOnFurnitureCategory("furniture")
            homeScreenViewModel.getProductBasedOnGroceryCategory("groceries")
            homeScreenViewModel.getProductBasedOnWomenDressCategory("womens-dresses")
            isDataLoaed.value = true
        }

    }


    AppScaffold(
        topAppBar = {
                    CustomTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                           containerColor = Color.White
                        ),
                        navigationIcon = {
                             Avatar(
                                 name = "S",
                                 fontFamily = FontFamily.Cursive,
                                 fontSize = 17.sp,
                                 modifier = Modifier
                                     .size(50.dp)
                                     .clickable {

                                     }
                             )
                        },
                        titleContent = { /*TODO*/ }
                    ) {
                    }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ },
        snackbarHost =  {}
    )
    { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

                .verticalScroll(
                    rememberScrollState()
                )
                .padding(top = 1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            // 1 -> 
            MarqueTab(list = listOf("FASTEST DELIVERY","100% GENUINE PRODUCTS", "PREMIMUM FINDS"))

            if(productBasedOnHotSales?.isNotEmpty() == true) {
                ShopEasePager(productBasedOnHotSales!![0].products,navController)
            }
            else {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .shimmerEffect(
                        RoundedCornerShape(0.dp)
                    ))
            }


            // -> 3. First Row contains filter method and all category product cards




            Row(
                modifier = Modifier
                    .background(color = Color(ShopAppConstants.cardLightColor))
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (getAllProductCategory.isNullOrEmpty()) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(2),
                        modifier = Modifier.height(150.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items(30) {
                            Box(
                                modifier = Modifier
                                    .width(170.dp)
                                    .height(60.dp)
                                    .padding(horizontal = 6.dp)
                                    .shimmerEffect(RoundedCornerShape(14.dp))
                            )
                        }
                    }
                    // Show shimmer loading indicator

                } else {
                    // Show the LazyHorizontalGrid with items
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(2),
                        modifier = Modifier.height(150.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(getAllProductCategory!!) { _: Int, item: AllProductCategory ->
                            ProductCategoryCard(productCategory = item)
                        }
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
                if(productBasedOnHotSales?.isNotEmpty() == true) {
                    CommonCategory(productBasedOnHotSales!![0].products,navController)
                }
                else {
                    LazyRow(modifier = Modifier.height(200.dp).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(10) {
                            Box(modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .shimmerEffect(
                                    RoundedCornerShape(12.dp)
                                ))
                        }
                    }

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
                if(productBasedOnBeauty?.isNotEmpty() == true) {
                    CommonCategory(productBasedOnBeauty!![0].products, navController)
                }
                else {
                    LazyRow(modifier = Modifier.height(200.dp).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(10) {
                            Box(modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .shimmerEffect(
                                    RoundedCornerShape(12.dp)
                                ))
                        }
                    }

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
                if(productBasedOnFurniture?.isNotEmpty() == true) {
                    CommonCategory(productBasedOnFurniture!![0].products,navController)
                }
                else {
                    LazyRow(modifier = Modifier.height(200.dp).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(10) {
                            Box(modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .shimmerEffect(
                                    RoundedCornerShape(12.dp)
                                ))
                        }
                    }

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
                if(productBasedOnGrocery?.isNotEmpty() == true) {
                    CommonCategory(productBasedOnGrocery!![0].products,navController)
                }
                else {
                    LazyRow(modifier = Modifier.height(200.dp).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(10) {
                            Box(modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .shimmerEffect(
                                    RoundedCornerShape(12.dp)
                                ))
                        }
                    }

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
                if(productBasedOnWomenDress?.isNotEmpty() == true) {
                    CommonCategory(productBasedOnWomenDress!![0].products,navController)
                }
                else {
                    LazyRow(modifier = Modifier.height(200.dp).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(10) {
                            Box(modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .shimmerEffect(
                                    RoundedCornerShape(12.dp)
                                ))
                        }
                    }

                }
            }



        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommonCategory(productBasedOnCategory: List<Product>,navController: NavController) {

    val scope = rememberCoroutineScope()
    LazyRow(
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 10.dp)){
        itemsIndexed(productBasedOnCategory){index: Int, item: Product ->

            Column(modifier = Modifier
                .clickable {

                    scope.launch {
                        withContext(Dispatchers.Main) {
                            navController.navigate("${Screen.DetailsScreen.route}/${item.id}")
                        }
                    }

                }
                .padding(horizontal = 10.dp)) {
//0xFFF7F7FF
                AsyncImageComponent(
                    imageUrl = item.thumbnail,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    color = generateRandomColor(),
                    badgeAvailable = true,
                    badgeName = item.availabilityStatus,
                    width = 200.dp,
                    height = 200.dp
                )
                CommonRow(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    HeaderText(title = item.title, modifier = Modifier
                        .width(150.dp)
                        .padding(bottom = 3.dp, top = 5.dp))
                    PriceText(title = item.price.toString())
                }

                DescriptionText(title = item.description, modifier = Modifier.width(200.dp))
            }

        }
    }
}