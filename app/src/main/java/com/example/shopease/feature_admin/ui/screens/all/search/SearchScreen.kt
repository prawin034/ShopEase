package com.example.shopease.feature_admin.ui.screens.all.search

import android.annotation.SuppressLint
import android.icu.text.ListFormatter.Width
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrandingWatermark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_admin.ui.viewModel.seach.SearchViewModel
import com.example.shopease.feature_common.components.AppBottomSheet
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.DescriptionText
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.TxtButton
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.generateRandomColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    commonViewModel: CommonViewModel,
    searchViewModel: SearchViewModel
) {


    val query by homeScreenViewModel.querySearch.observeAsState("")
    val active  by homeScreenViewModel.activeSearch.observeAsState(false)
    val searchList by  remember { mutableStateOf(homeScreenViewModel.searchHistoryList) }
    val searchProducts by searchViewModel.searchProducts.observeAsState()

    val sheetState = remember { mutableStateOf(false) }
    val currentSheetContent = remember { mutableStateOf(BottomSheetContent.SORT) }
    val focusRequester = remember {FocusRequester()}

    LaunchedEffect(Unit){
        focusRequester.requestFocus()
        homeScreenViewModel.updateActiveSearch(true)
    }
    Scaffold { it ->

        androidx.compose.material3.SearchBar(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .padding(bottom = 0.dp)
                .focusRequester(focusRequester)
            ,
            query = query,
            onQueryChange = {value ->
                homeScreenViewModel.updateQuerySearch(value)
                //searchViewModel.searchProducts(query)
            },
            shape = RoundedCornerShape(8.dp),
            onSearch =  {
                homeScreenViewModel.updateActiveSearch(false)
                if(query.isNotBlank()) {

                homeScreenViewModel.addSearchHistory(query.trimEnd())
                searchViewModel.searchProducts(query.trimEnd(), sortBy= "title", order = "asc")
                searchViewModel.clearBrandList()
                searchViewModel.clearCheckList()
                }
            },
            active = active ,
//
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon", tint = Color(ShopAppConstants.AppPrimaryColor))
            },
            placeholder = {
                AppTxt(text = "Search")
            },
            onActiveChange = {homeScreenViewModel.updateActiveSearch(it)
            },
            trailingIcon = {
                if(active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if(query.isNotEmpty()) {
                            homeScreenViewModel.updateQuerySearch("")
                            }
                            else {
                                homeScreenViewModel.updateActiveSearch(false)
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            }
        ) {
            searchList.forEach {
                Row(
                    modifier = Modifier
                        .clickable {
                            searchViewModel.searchProducts(query, sortBy = "title", order = "asc")
                            homeScreenViewModel.updateActiveSearch(false)
                            homeScreenViewModel.updateQuerySearch(it)
                        }
                        .fillMaxWidth()

                        .padding(all = 14.dp)
                )
                {


                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    AppTxt(text = it)


                }

            }


        }



        AppScaffold(
            topAppBar = {
                CommonRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 69.dp)

                    .horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically)
                {

                    TxtButton(text = "Sort by",
                        `font-size` = 10.sp,
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        modifier = Modifier
                            .border(1.dp, color = Color.LightGray)
                            .clip(
                                RoundedCornerShape(12.dp)
                            ),
                        icon = true,
                        iconFound = Icons.Default.KeyboardArrowDown
                    ) {
                        sheetState.value = true
                        currentSheetContent.value = BottomSheetContent.SORT

                    }

                    TxtButton(text = "Filter",
                        `font-size` = 10.sp,
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        modifier = Modifier
                            .border(1.dp, color = Color.LightGray)
                            .clip(
                                RoundedCornerShape(12.dp)
                            ),
                        icon = true,
                        iconFound = Icons.Default.FilterList
                    ) {
                        sheetState.value = true
                        currentSheetContent.value = BottomSheetContent.FILTER
                    }

                    TxtButton(text = "Price",
                        `font-size` = 10.sp,
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        modifier = Modifier
                            .border(1.dp, color = Color.LightGray)
                            .clip(
                                RoundedCornerShape(12.dp)
                            ),
                        icon = true,
                        iconFound = Icons.Default.PriceCheck
                    ) {
                        sheetState.value = true
                        currentSheetContent.value = BottomSheetContent.PRICE
                    }

                    TxtButton(text = "Brand",
                        `font-size` = 10.sp,
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        modifier = Modifier
                            .border(1.dp, color = Color.LightGray)
                            .clip(
                                RoundedCornerShape(12.dp)
                            ),
                        icon = true,
                        iconFound = Icons.Default.BrandingWatermark
                    ) {
                        sheetState.value = true
                        currentSheetContent.value = BottomSheetContent.BRAND
                    }

                }
            },
            bottomAppBar = { /*TODO*/ },
            floatingButton = { /*TODO*/ },
            modifier = Modifier.padding(it),
            snackbarHost = {}
        )
        {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 40.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Product(searchProducts?.products,homeScreenViewModel,searchViewModel,navController)

            }


        }

    }
    
    
    
    AppBottomSheet(showContent = sheetState, onDismissRequest = {
       sheetState.value = false
    })
    {

        when(currentSheetContent.value) {
            BottomSheetContent.SORT -> SortContents(searchViewModel,homeScreenViewModel,sheetState)
            BottomSheetContent.FILTER -> FilterContent()
            BottomSheetContent.PRICE -> PriceContent()
            else -> BrandContent(searchProducts?.products,searchViewModel,homeScreenViewModel)
        }
        
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Product(
    products: List<Product>?,
    homeScreenViewModel: HomeScreenViewModel,
    searchViewModel: SearchViewModel,
    navController :NavController
) {
     val scope = rememberCoroutineScope()
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalArrangement = Arrangement.SpaceBetween) {
        itemsIndexed(products ?: emptyList()) { _: Int, item: Product ->


            Column {
                Box(modifier = Modifier
                    .height(200.dp)
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
                        imageUrl = item.thumbnail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(6.dp),
                        color = generateRandomColor(),
                        contentScale = ContentScale.FillBounds
                    )
                }

                Column(modifier = Modifier.padding(top = 4.dp)) {
                    AppTxt(text = item.title, fontSize = 10.sp)
                    DescriptionText(title = item.description, fontSize = 8, modifier = Modifier.padding(4.dp))

                    CommonRow(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 3.dp)) {
                        PriceText(title = "$".plus(item.price.toString()))

                        TxtButton(text = "Add", `font-size` = 10.sp, modifier = Modifier
                            .width(50.dp)
                            .padding(horizontal = 6.dp)) {

                        }
                    }
                }
            }





        }
    }


    if(products?.isEmpty() == true) {
        EmptyData()
    }

}


@Composable
fun EmptyData() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppTxt(text = "No Data Found!", fontSize = 17.sp, textColor = Color.Black)
    }



}






enum class BottomSheetContent {
    SORT,FILTER,PRICE,BRAND
}

enum class SortContent(val displayName :String) {
    RELEVANCE("Relevance"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    ALPHABETICAL_A_TO_Z("Alphabetical: A to Z"),
    ALPHABETICAL_Z_TO_A("Alphabetical: Z to A")

}



@SuppressLint("SuspiciousIndentation")
@Composable
fun SortContents(
    searchViewModel: SearchViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    sheetState: MutableState<Boolean>
) {

   /*
   Price: Low to High
Price: High to Low
Rating: High to Low
Newest Arrivals
Alphabetical: A to Z
Alphabetical: Z to A
    */

  //  val sortList = listOf("Relevance","Price : Low to High", "Price : High to Low", "Alphabetical : A to Z", "Alphabetical : Z to A")

    val sortedOption = rememberSaveable { mutableStateOf(SortContent.RELEVANCE) }
    val query by homeScreenViewModel.querySearch.observeAsState("")
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
        
        SortContent.entries.forEachIndexed { _, item ->
            CommonRow(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {

                AppTxt(text = item.displayName, modifier = Modifier.padding(horizontal = 10.dp))

                RadioButton(selected = sortedOption.value == item, onClick = {
                  sortedOption.value = item
                    performSort(searchViewModel, sortedOption,query)
                   sheetState.value = false
                })

            }
        }
    }
}



fun performSort(searchViewModel: SearchViewModel, sortedOption: MutableState<SortContent>,query :String){
    when (sortedOption.value) {
        SortContent.PRICE_LOW_TO_HIGH -> {
            searchViewModel.searchProducts(query, sortBy= "price", order = "asc")
        }
        SortContent.PRICE_HIGH_TO_LOW -> {
            searchViewModel.searchProducts(query, sortBy= "price", order = "desc")
        }
        SortContent.ALPHABETICAL_A_TO_Z -> {
            searchViewModel.searchProducts(query, sortBy= "title", order = "asc")
        }
        SortContent.ALPHABETICAL_Z_TO_A -> {
            searchViewModel.searchProducts(query, sortBy= "title", order = "desc")
        }
        else  -> {
            searchViewModel.searchProducts(query, sortBy= "title", order = "asc")
        }
    }
}

@Composable
fun FilterContent() {
    AppTxt(text = "Filter")
}


@Composable
fun PriceContent() {
    AppTxt(text = "Price")
}


@Composable
fun BrandContent(products: List<Product>?,
searchViewModel: SearchViewModel,
                 homeScreenViewModel: HomeScreenViewModel
) {



    val checkList  by remember { mutableStateOf(searchViewModel.checkList) }


    val brands  by remember { mutableStateOf(searchViewModel.brandsList) }
    val query by homeScreenViewModel.querySearch.observeAsState()


    LaunchedEffect(Unit) {
        if(brands.isEmpty()) {
            for (item  in products ?: emptyList()) {
                searchViewModel.updateBrandList(item.brand ?:"")
            }
        }

    }


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {

        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            itemsIndexed(brands) {index,item ->

                CommonRow(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checkList.contains(item), onCheckedChange = {
                        if(checkList.contains(item)) {
                            searchViewModel.removeCheckListItem(item)
                            searchViewModel.searchProducts(query ?:"", sortBy= "title", order = "asc")

                        }
                        else {
                            searchViewModel.addCheckListItem(item)
                        }
                    })
                    AppTxt(text = item ?:"", fontSize = 10.sp, modifier = Modifier.padding(horizontal = 3.dp), textAlign = TextAlign.Start)
                }

            }
        }






        CommonRow(modifier = Modifier.padding(5.dp)) {

            TxtButton(
                text = "Clear",
                `font-size` = 10.sp,
                backgroundColor = Color(0xFF81819E),
                textColor = Color.Black,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(1.dp)
            ) {
                searchViewModel.clearCheckList()
                searchViewModel.searchProducts(query ?:"", sortBy= "title", order = "asc")

            }

            TxtButton(
                text = "Apply",
                `font-size` = 10.sp,
                backgroundColor = Color(ShopAppConstants.AppPrimaryColor),
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(1.dp)
            ) {
//                searchViewModel.filterSearchList(list = checkList)
                searchViewModel.FilterProducts(query ?:"", sortBy = "title", order = "asc", selectedBrand = checkList)
            }



        }


    }
}