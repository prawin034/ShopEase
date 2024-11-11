package com.example.shopease.feature_admin.ui.screens.all.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.data.model.CardList
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
import java.util.UUID


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
                    LoadCardList(cardList , cardViewModel)
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
                "Details" ->  CardDetails1(pager,cardViewModel,  bottomSheetState)
                "CardStyle" -> CardDetails2(pager,cardViewModel,  bottomSheetState)
            }
            
            
        }








    }

}



@Composable
fun CardDetails1(
    pager: PagerState,
    cardViewModel: CardViewModel,
    bottomSheetState: MutableState<Boolean>
) {
    val scope = rememberCoroutineScope()

    var cardList  by remember { mutableStateOf(CardList(UUID.randomUUID().toString(),"","","","","","")) }
    
    val binlookup by cardViewModel.binlookupList.observeAsState(initial = emptyList())
//    val focusRequester = remember {FocusRequester()}
    val keyboardController = LocalSoftwareKeyboardController.current
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
            value = cardList.cardNumber.toString(),
            label = {
                AppTxt(text = "Card Number")
            },
            leadingIcon = { /*TODO*/ },
            trailingIcon = {
                if(binlookup.isEmpty()){
                Icon(imageVector = Icons.Default.CreditCard, contentDescription = "Card", tint = Color.Black)
                }
                else {
                 // AppTxt(text = binlookup[0].cardBrand)
                  Image(painter = painterResource(id = mapCardCategoryToImage(binlookup[0].cardBrand)), contentDescription = "", contentScale = ContentScale.Fit, modifier = Modifier
                      .width(80.dp)
                      .height(60.dp))
                }
            },
            placeHolder = {

                AppTxt(text = "XXXX XXXX XXXX XXXX")
            },
            visualTransformation = CardNumberFormatTransformation(),

            onValueChange = {newValue ->
                            cardList = cardList.copy(cardNumber = newValue)
                if (newValue.length >= 6 && newValue.all { it.isDigit() }) {
                    // Convert to integer and call getBinList
                   cardViewModel.getBinList(newValue.take(6).toInt())
                }
                else {
                    cardViewModel.clearbin()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        if(binlookup.isNotEmpty()) {
            cardList = cardList.copy(cardBrand = binlookup[0].cardBrand)
            cardList = cardList.copy(cardName = binlookup[0].cardType)
        }

        SpacerCommon()
        SpacerCommon()




        CmnTxtField(
            value = cardList.cardHolderName ?:"",
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

            onValueChange = {
                             cardList = cardList.copy(cardHolderName = it)
            },
            modifier = Modifier.fillMaxWidth()
        )



        SpacerCommon()
        SpacerCommon()


        CommonRow {



            CmnTxtField(
                value = cardList.expiryDate ?:"",
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
                visualTransformation = expiryDateTransformation(),
                onValueChange = {
                             cardList = cardList.copy(expiryDate = it)
                },
                modifier = Modifier
                    .weight(0.3f)
                    .padding(5.dp)
            )


            CmnTxtField(
                value = cardList.cvv ?:"",
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
                onValueChange = {
                       cardList = cardList.copy(cvv = it)
                },
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
                keyboardController?.hide()
                coroutineScope {
                    pager.animateScrollToPage(1)

                  val id =   cardViewModel.addToCard(cardList)
                    cardViewModel.addCardId(id)
                }
            }



        }


        SpacerCommon()
        SpacerCommon()





    }
}

@Composable
fun CardDetails2(pager: PagerState, cardViewModel: CardViewModel, value: MutableState<Boolean>) {
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




        MyCard(cardViewModel,value )





    }

}






@SuppressLint("SuspiciousIndentation")
@Composable
fun MyCard(cardViewModel: CardViewModel, value: MutableState<Boolean>){
      val colorsList = listOf("Dark-Purple","Black","Blue","Green","Dark-Gray","Pink","orange")
    val selectedColor by remember { mutableStateOf(cardViewModel.selectedColorName) }



    //var updatedCardList by remember { mutableStateOf(CardList("","","","","")) }



    val cardId  = cardViewModel.cardId


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
                .border(
                    1.dp,
                    color = if (selectedColor.value == s) Color.Blue else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
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
        cardViewModel.updateCardColorById(id = cardId.value, cardColor = mapColor(selectedColor.value))
        value.value = false

        Log.d("CarList","${cardViewModel.cardList}")
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



class CardNumberFormatTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Keep only the first 16 digits of input and apply the format
        val trimmedText = text.text.filter { it.isDigit() }.take(16)

        // Insert spaces after every 4th character for the formatted display
        val formattedText = buildString {
            trimmedText.forEachIndexed { index, char ->
                if (index > 0 && index % 4 == 0) append(" ")
                append(char)
            }
            if (length < 19) { // 19 = 16 digits + 3 spaces
                for (i in length until 19) {
                    if (i % 5 == 4) append(" ") // Space every 4 digits
                    else append("X")
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Calculate how many spaces have been added by the offset position
               val transformedOffset = offset + (offset /4)
                return transformedOffset.coerceAtMost(formattedText.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val originalOffset = offset  - (offset /5)
                return originalOffset.coerceAtMost(trimmedText.length)
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = offsetMapping
        )
    }
}



class expiryDateTransformation() : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val trimmedText =  text.text.filter { it.isDigit() }.take(4)



        val formatText  = buildString {

            trimmedText.mapIndexed {index: Int, c: Char ->
                if(index == 2) append("/")
                append(c)
            }.joinToString("")

            while (length < 5) {
                if(length == 2) append("/")
                if(length <2) append("M")
                if(length >2) append("Y")
            }


        }


        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
              if(offset <=0) return  offset

               val formattedOffset = offset + (offset /2)
               return  formattedOffset.coerceAtMost(formatText.length)


            }

            override fun transformedToOriginal(offset: Int): Int {
                // Mapping from transformed back to original text positions
                val transformedOff = offset - (offset /2)
                return  transformedOff.coerceAtMost(trimmedText.length)

            }
        }


        return  TransformedText(
            text = AnnotatedString(formatText),
            offsetMapping = offsetMapping
        )


    }

}



fun mapCardCategoryToImage(name:String) : Int {



    return  when(name.lowercase()) {
        "visa"  -> com.example.shopease.R.drawable.visa
        "rupay" ->  com.example.shopease.R.drawable.rupay
        "mastercard" -> com.example.shopease.R.drawable.master
         else -> 0
    }
}



@Composable
fun  LoadCardList(cardList: List<CardList>, cardViewModel: CardViewModel) {

    LazyColumn {
        itemsIndexed(cardList) { index, item ->
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(16.dp), // Add padding around the card for spacing
                colors = CardDefaults.cardColors(
                    containerColor = item.cardColor,
                    contentColor = Color.White
                ),
                enableDefaultPadding = true
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Padding inside the card for content spacing
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    // Top Section with Bank Name and Card Type
                    CommonRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        AppTxt(text = "Bank Name", fontSize = 14.sp, textColor = Color.White)
                        AppTxt(text = "Card Type", fontSize = 14.sp, textColor = Color.White)
                    }

                    Spacer(modifier = Modifier.height(24.dp)) // Space between top and card number

                    // Centered Card Number
                    AppTxt(
                        text = item.cardNumber ?: "",
                        letterSpacing = 6.sp,
                        textColor = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(32.dp)) // Space before the bottom section

                    // Bottom Section with Card Holder and Expiry Date
                    CommonRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Card Holder Information
                        Column(horizontalAlignment = Alignment.Start) {
                            AppTxt(
                                text = "Card Holder",
                                fontSize = 10.sp,
                                textColor = Color.White,
                                modifier = Modifier.padding(bottom = 4.dp),
                                textAlign = TextAlign.Center,
                            )
                            AppTxt(
                                text = item.cardHolderName ?: "",
                                fontSize = 12.sp,
                                letterSpacing = 2.sp,
                                textColor = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        }

                        // Expiry Date Information
                        Column(horizontalAlignment = Alignment.End) {
                            AppTxt(
                                text = "Expiry",
                                fontSize = 10.sp,
                                textColor = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            AppTxt(
                                text = item.expiryDate ?: "",
                                fontSize = 12.sp,
                                letterSpacing = 2.sp,
                                textColor = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

}