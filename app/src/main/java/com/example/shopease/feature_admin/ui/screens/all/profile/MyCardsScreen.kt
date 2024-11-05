package com.example.shopease.feature_admin.ui.screens.all.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.ui.screens.all.search.EmptyData
import com.example.shopease.feature_admin.ui.viewModel.CardViewModel
import com.example.shopease.feature_common.components.AppBottomSheet
import com.example.shopease.feature_common.components.AppCard
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.CmnTxtField
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.SpacerCommon
import com.example.shopease.feature_common.components.TxtButton
import com.example.shopease.feature_common.components.iconBtn
import com.example.shopease.feature_common.utils.ShopAppConstants
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(navController: NavController,cardViewModel: CardViewModel) {
    val bottomSheetState = remember { mutableStateOf(false) }
    val cardList   by remember { mutableStateOf(cardViewModel.cardList) }
    AppScaffold(
        topAppBar = {
           CustomTopAppBar(
               navigationIcon = {

               },
               titleContent = {

                   AppTxt(text = "My Cards")
               }
           ) {

               TxtButton(
                   text = "New Card",
                   `font-size` = 12.sp,
                   textColor = Color.Black,
                   backgroundColor = Color.Transparent,
                   icon = true,
                   iconFound = Icons.Default.Add,
                   appendIconFront = true
               ) {
                   bottomSheetState.value = !bottomSheetState.value

               }

           }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(it)
                .padding(bottom = 20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            when {
                cardList.isNotEmpty() -> {
                    cardList.forEachIndexed { index, cardList ->

                        AppTxt(text = cardList.cardNumber.toString())


                    }
                }

                else -> {


                    EmptyData()


                }
            }



        }

        CardDetailsSheet(bottomSheetState,navController,cardViewModel)
    }

}



@Composable
fun CardDetailsSheet(
    bottomSheetState: MutableState<Boolean>,
    navController: NavController,
    cardViewModel: CardViewModel
) {


    val myPage = listOf("Details","CardStyle")
    val pager= rememberPagerState(initialPage = 0) {
        myPage.size
    }

    AppBottomSheet(showContent = bottomSheetState,
        onDismissRequest = {
            bottomSheetState.value = false
        }
    ) {


        HorizontalPager(
            modifier = Modifier,
            state = pager,
            pageSpacing = 0.dp,
            userScrollEnabled = false,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),

            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pager),
            key = {myPage[it]},
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                state = pager,
                orientation = Orientation.Horizontal
            ),
        ) {
            
            val myScreen = myPage[it]
            
            when(myScreen){
                "Details" ->  CardDetails1(pager)
                "CardStyle" -> CardDetails2(pager,cardViewModel)
            }
            
            
        }








    }

}



@Composable
fun CardDetails1(pager: PagerState) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        AppTxt(text = "Add Details", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp), textAlign = TextAlign.Start, fontSize = 16.sp)
        AppTxt(text = "Step 1/2" , fontSize = 11.sp, textColor = Color.LightGray, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)


        SpacerCommon()
        SpacerCommon()



        CmnTxtField(
            value = "",
            label = {
                AppTxt(text = "Card Number")
            },
            leadingIcon = { /*TODO*/ },
            trailingIcon = {
                Icon(imageVector = Icons.Default.CreditCard, contentDescription = "Card", tint = Color.Black)
            },
            placeHolder = {
                AppTxt(text = "XXXX XXXX XXXX XXXX ")
            },
            visualTransformation = VisualTransformation.None,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        SpacerCommon()
        SpacerCommon()




        CmnTxtField(
            value = "",
            label = {
                AppTxt(text = "CardHolder Name")
            },
            leadingIcon = { /*TODO*/ },
            trailingIcon = {
                Icon(imageVector = Icons.Default.PersonOutline, contentDescription = "Card", tint = Color.Black)
            },
            placeHolder = {

            },
            visualTransformation = VisualTransformation.None,

            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )



        SpacerCommon()
        SpacerCommon()


        CommonRow {



            CmnTxtField(
                value = "",
                label = {
                    AppTxt(text = "Expiry")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { /*TODO*/ },
                trailingIcon = {

                },
                placeHolder = {
                    AppTxt(text = "MM/YY")
                },
                visualTransformation = VisualTransformation.None,
                onValueChange = {},
                modifier = Modifier
                    .weight(0.3f)
                    .padding(5.dp)
            )


            CmnTxtField(
                value = "",
                label = {
                    AppTxt(text = "Cvv")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { /*TODO*/ },
                trailingIcon = {

                },
                placeHolder = {

                },
                visualTransformation = VisualTransformation.None,
                onValueChange = {},
                modifier = Modifier
                    .weight(0.3f)
                    .padding(5.dp)
            )


        }



        SpacerCommon()
        SpacerCommon()





        TxtButton(text = "Next", `font-size` = 13.sp, textColor = Color.White,
            backgroundColor = Color(ShopAppConstants.AppPrimaryColor),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp)
        ) {

            scope.launch {
                coroutineScope {
                    pager.animateScrollToPage(1)
                }
            }



        }



        SpacerCommon()
        SpacerCommon()





    }
}

@Composable
fun CardDetails2(pager: PagerState,cardViewModel: CardViewModel) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )

    {


        CommonRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically)
        {


            iconBtn(icon = Icons.Default.ArrowBack, colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            ))
            {
                scope.launch {
                    coroutineScope {
                pager.animateScrollToPage(0)

                    }
                }
            }
            AppTxt(
                text = "Card Style", modifier = Modifier

                    .padding(bottom = 4.dp)
                    .padding(horizontal = 4.dp), textAlign = TextAlign.Start, fontSize = 16.sp
            )
        }

        AppTxt(
            text = "Step 2/2",
            fontSize = 11.sp,
            textColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            textAlign = TextAlign.Start
        )


        SpacerCommon()
        SpacerCommon()




        MyCard(cardViewModel )





    }

}






@Composable
fun MyCard(cardViewModel: CardViewModel){
      val colorsList = listOf("Dark-Purple","Black","Blue","Green","Dark-Gray","Pink","orange")
    val selectedColor by remember { mutableStateOf(cardViewModel.selectedColorName) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), contentAlignment = Alignment.TopEnd
        ) {
            AppCard(
                colors = CardDefaults.cardColors(
                    containerColor = mapColor(selectedColor.value)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp),

                )
            {




            }



            AppTxt(
                text = "Physical",
                textColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.End
            )

        }


    SpacerCommon()
    SpacerCommon()
    SpacerCommon()
    SpacerCommon()
    CommonRow(modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()))
    {
        colorsList.forEachIndexed { index, s ->


            Column(modifier = Modifier

                .padding(4.dp)
                .border(1.dp, color = if(selectedColor.value == s) Color.Blue else Color.Transparent, shape = RoundedCornerShape(12.dp))
                .padding(4.dp)
                .padding(horizontal = 30.dp)

                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Box(
                    modifier = Modifier
                        .clickable {
                            cardViewModel.updateSelectedColor(s)
                        }
                        .width(100.dp)
                        .height(100.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = mapColor(s))
                )

                AppTxt(text = s, textAlign = TextAlign.Center, fontSize = 10.sp, modifier = Modifier.padding(top = 5.dp))


            }


        }
    }


    SpacerCommon()

    SpacerCommon()

    SpacerCommon()


    TxtButton(text = "Add", `font-size` = 13.sp, textColor = Color.White,
        backgroundColor = Color(ShopAppConstants.AppPrimaryColor),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(65.dp)
    ) {

    }


}


fun mapColor(colorName:String) :Color {

    return  when(colorName) {
        "Dark-Purple" -> Color(ShopAppConstants.AppPrimaryColor)
        "Black" -> Color(0xFF000000)
        "Blue" -> Color(0xFF2196F3)
        "Green" -> Color(0xFF4CAF50)
        "Dark-Gray" -> Color(0xFF3C3C41)
        "Pink" -> Color(0xFFE91E63)
        "orange" -> Color(0xFFFF5722)
        else -> Color(ShopAppConstants.AppPrimaryColor)

    }
}