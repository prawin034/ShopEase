package com.example.shopease.feature_admin.ui.screens.all.categories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.data.model.AllProductCategory
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.ProductCategory
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_admin.ui.screens.all.search.EmptyData
import com.example.shopease.feature_admin.ui.screens.all.search.Product
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_admin.ui.viewModel.seach.SearchViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.DescriptionText
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.TxtButton
import com.example.shopease.feature_common.components.iconBtn
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor
import com.example.shopease.feature_common.utils.productCategoryUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel,
    searchViewModel: SearchViewModel,
    commonViewModel: CommonViewModel
){
    val getAllProductCategory by  homeScreenViewModel.getAllProductCategory.observeAsState(initial = emptyList())

    val productBasedOnCategory by homeScreenViewModel.getProductBasedOnCategory.observeAsState()
    var isDataLoaded by remember { mutableStateOf(false) }
    val selectedTab = remember { mutableStateOf(0) }
    LaunchedEffect(Unit){
        if(!isDataLoaded) {
        homeScreenViewModel.getAllProductsCategory()
        homeScreenViewModel.getProductBasedCategory("beauty")
            isDataLoaded = true
        }
    }
    AppScaffold(
        topAppBar = {
           CustomTopAppBar(
               navigationIcon = { /*TODO*/ },
               titleContent = {
                   AppTxt(text = "All Categories")
               })
           {
               CommonRow(modifier = Modifier.padding(horizontal = 3.dp)) {
                   iconBtn(icon = Icons.Default.Search, colors = IconButtonDefaults.iconButtonColors(
                       containerColor = Color.Transparent,
                       contentColor = Color.Black
                   )) {
                       commonViewModel.changeActiveTab(1)
                   }

                   iconBtn(icon = Icons.Default.Mic, colors = IconButtonDefaults.iconButtonColors(
                       containerColor = Color.Transparent,
                       contentColor = Color.Black
                   )) {

                   }
               }

           }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {


            Row(modifier = Modifier
                .fillMaxSize(1f)
                ) {

                // 1 first column
                    LazyColumn(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(bottom = 50.dp)
                    ) {
                        itemsIndexed(
                            getAllProductCategory ?: emptyList()
                        ) { index: Int, item: AllProductCategory ->

                            Column(
                                modifier = Modifier
                                    .background(
                                        color = if (selectedTab.value == index) Color.White else Color(
                                            ShopAppConstants.cardLightColor
                                        )
                                    )

                                    .fillMaxWidth(0.3f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImageComponent(
                                    color =   if(selectedTab.value == index) Color(ShopAppConstants.AppPrimaryColor) else Color.Transparent,
                                    imageUrl = productCategoryUrl(item.slug),
                                    shape = if(selectedTab.value == index)  CircleShape else RoundedCornerShape(12.dp) ,
                                    modifier = Modifier
                                        .clickable {
                                            selectedTab.value = index
                                            homeScreenViewModel.getProductBasedCategory(
                                                item.slug?.trimEnd() ?: ""
                                            )
                                        }
                                        .fillParentMaxWidth(0.3f)
                                        .height(70.dp)
                                        .padding(bottom = 10.dp)
                                        .padding(6.dp)
                                )
                                AppTxt(text = item.name ?:"", fontSize = 8.sp, textColor = if(selectedTab.value == index) Color(ShopAppConstants.AppPrimaryColor) else Color.Black)

                                Divider(color = Color.LightGray, thickness = 0.4.dp, modifier = Modifier.padding(top = 2.dp))

                            }






                        }
                    }


                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .navigationBarsPadding()
                        .padding(bottom = 40.dp)
                        .weight(0.7f)
                // Adjust width as needed
                ) {


                    ProductCategory(
                        productBasedOnCategory?.products,
                        homeScreenViewModel,
                        navController
                    )

                }
            }






            

           
        }

    }

}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductCategory(
    products: List<Product>?,
    homeScreenViewModel: HomeScreenViewModel,
    navController :NavController
) {
    val scope = rememberCoroutineScope()
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalArrangement = Arrangement.SpaceBetween) {
        itemsIndexed(products ?: emptyList()) { _: Int, item: Product ->


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier
                    .height(100.dp)
                    .clip(CircleShape)
                    .clickable {
                        scope.launch {
                            withContext(Dispatchers.Main) {
                                navController.navigate("${Screen.DetailsScreen.route}/${item.id}") {
                                    launchSingleTop = true
                                }
                            }
                        }
                    }) {
                    AsyncImageComponent(
                        shape = CircleShape,
                        imageUrl = item.thumbnail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(6.dp).padding(bottom = 6.dp),
                        color = generateRandomColor(),
                        contentScale = ContentScale.Inside
                    )
                }

                Column(modifier = Modifier.padding(top = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    AppTxt(text = item.title, fontSize = 8.sp)
                }
            }





        }
    }


    if(products?.isEmpty() == true) {
        EmptyData()
    }

}