package com.example.shopease.feature_admin.ui.screens.all.details

import android.annotation.SuppressLint
import android.os.Build
import android.os.strictmode.UnbufferedIoViolation
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.shopease.feature_admin.data.model.AddToCarProduct
import com.example.shopease.feature_admin.data.model.AddToCartRequest
import com.example.shopease.feature_admin.data.model.Product
import com.example.shopease.feature_admin.data.model.Review
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.cart.CartViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_common.components.AppCard
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.BadgeLabel
import com.example.shopease.feature_common.components.`Col-2-row`
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.DescriptionText
import com.example.shopease.feature_common.components.HeaderText
import com.example.shopease.feature_common.components.ImagePopUp
import com.example.shopease.feature_common.components.MinusButton
import com.example.shopease.feature_common.components.MoreIconButton
import com.example.shopease.feature_common.components.PlusButton
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.ProductText
import com.example.shopease.feature_common.components.ProductTextHeader
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.components.SeeAllText
import com.example.shopease.feature_common.components.SpacerCommon
import com.example.shopease.feature_common.components.StarRating
import com.example.shopease.feature_common.components.TxtButton
import com.example.shopease.feature_common.components.iconBtn
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_common.utils.parseData
import kotlinx.coroutines.launch
import okhttp3.internal.wait


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    id: Int?,
    cartViewModel: CartViewModel,
    commonViewModel: CommonViewModel
) {


    BackHandler {
        navController.popBackStack()
        cartViewModel.clearCounter()
    }
    val context = LocalContext.current
    val product by homeScreenViewModel.getSingleProduct.observeAsState(initial = emptyList())
    val sucessMessage by cartViewModel.successMessage.observeAsState()
    val failureMessage by cartViewModel.failureMessage.observeAsState()
    LaunchedEffect(Unit){

        if(id !=null){
            homeScreenViewModel.getSingleProduct(id)
        }
    }
    val scope = rememberCoroutineScope()
    val snackbarHost = remember { SnackbarHostState() }


    AppScaffold(

        topAppBar = {
           CustomTopAppBar(

               navigationIcon = {
                      BackIconButton(color = Color.Black) {
                          
                      }          
               },
               titleContent = {
                       SectionTitleTxt(title = "Details Products", color = Color.Black)
               },
               actions = {
                   MoreIconButton(color = Color.Black) {

                   }
               }
           )        
        },
        bottomAppBar = {

                AddToCartBottomBar(navController,cartViewModel,product,commonViewModel,snackbarHost)



        },
        floatingButton = {

        },
        snackbarHost =  {
            SnackbarHost(hostState = snackbarHost){
                Snackbar(
                    snackbarData = it,
                    actionColor = Color.White,
                    containerColor = Color(ShopAppConstants.AppPrimaryColor),
                    contentColor = Color.White
                )
            }
        }
    )
    {
        
        if(product?.isNotEmpty() == true) {
        DetailProduct(navController,homeScreenViewModel, product!!,it,cartViewModel)
        }



//        if(sucessMessage !=null) {
//            Toast.makeText(context,"$sucessMessage",Toast.LENGTH_SHORT).show()
//           cartViewModel.clearToast()
//        }
        if(failureMessage !=null) {
            Toast.makeText(context,"$failureMessage",Toast.LENGTH_SHORT).show()
        cartViewModel.clearToast()
        }
    }
    
}





@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailProduct(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    product: List<Product>,
    it: PaddingValues,
    cartViewModel: CartViewModel
) {

    val scope = rememberCoroutineScope()
    val showFullText = remember { mutableStateOf(true) }
    val statePopUp = remember { mutableStateOf(false) }


    val zoomIn : () -> Unit =  {

    }

    val zoomOut : () -> Unit = {

    }
    LazyColumn(contentPadding = it) {
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
                                .clickable {
                                    statePopUp.value = !statePopUp.value
                                }
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
                    PriceText(fontSize = 13, title = "$${item.price}", color = Color(ShopAppConstants.AppPrimaryColor))


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


            Spacer(modifier = Modifier.height(10.dp))
            
            // -> Product Information
            ProductInformation(item)
            
            // Reviews
            ReviewsProduct(item, item.reviews)



            ImagePopUp(state = statePopUp,
                onDismiss = {
                statePopUp.value = false
            })
            {

                AppScaffold(
                    containerColor =  Color(ShopAppConstants.cardLightColor),
                    topAppBar = {
                        CustomTopAppBar(
                            navigationIcon = { /*TODO*/ },
                            titleContent = { /*TODO*/ })
                        {
                            iconBtn(icon = Icons.Default.Close, colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color(ShopAppConstants.AppPrimaryColor)
                            )) {
                                statePopUp.value = false

                            }

                        }
                    },
                    bottomAppBar = { /*TODO*/ },
                    floatingButton = { /*TODO*/ },
                    snackbarHost = { /*TODO*/ })
                {
                    Column {
                        Column(modifier = Modifier.fillMaxSize() ){

                            val pagerPop = rememberPagerState(initialPage = 0, pageCount = { item.images.size })
                            var scale by remember { mutableFloatStateOf(1f) } // Zoom level
                            var offset by remember { mutableStateOf(Offset.Zero) }
                            //var isZoomedIn by remember { mutableStateOf(false)
                            val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->

                                scale = (scale * zoomChange).coerceIn(1f,3f)
                                offset += offsetChange
                            }
                            HorizontalPager(
                                modifier = Modifier,
                                state = pagerPop,
                                pageSpacing = 0.dp,
                                userScrollEnabled = scale == 1f,
                                reverseLayout = false,
                                contentPadding = PaddingValues(0.dp),

                                pageSize = PageSize.Fill,
                                flingBehavior = PagerDefaults.flingBehavior(state = pagerPop),
                                key = {item.images[it]},
                                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                                    state = pagerPop,
                                    orientation = Orientation.Horizontal
                                ),
                            ) { index ->

                                val showImage = item.images[index]
                                Box(contentAlignment = Alignment.TopEnd) {
                                    AsyncImageComponent(
                                        contentScale = ContentScale.FillWidth,
                                        imageUrl = showImage,
                                        modifier = Modifier

                                            .pointerInput(Unit) {
                                                detectTapGestures(
                                                    onDoubleTap = {
                                                        scale = if (scale > 1f) 1f else 2f
                                                        if (scale == 1f) {
                                                            offset = Offset.Zero
                                                        }
                                                    },
                                                )

                                            }

                                            .animateContentSize()

                                            .graphicsLayer(
                                                scaleX = scale,
                                                scaleY = scale,
                                                transformOrigin = TransformOrigin.Center,
                                                translationX = offset.x,
                                                translationY = offset.y,
                                            )
                                            .transformable(state, enabled = scale >1f)
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.92f),
                                        shape = RoundedCornerShape(0.dp),
                                        color = Color(ShopAppConstants.cardLightColor)
                                    )

                                }

                            }


                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 20.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    item.images.forEachIndexed { SubIndex, item ->

                                        Canvas(modifier = Modifier
                                            .padding(horizontal = 5.dp)
                                            .size(10.dp)) {
                                            drawCircle(
                                                color = if(SubIndex == pagerPop.currentPage) Color.Red else Color.LightGray,

                                                )
                                        }
                                    }
                                }
                            }



                        }
                    }
                    


                }


                // load images




            }

            

            
        }
    }

    
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReviewsProduct(item: Product, reviews: List<Review>) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        SectionTitleTxt(title = "Reviews")

        SpacerCommon()
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SectionTitleTxt(title = "Overall Rating")
            SpacerCommon()
            
            ProductText(
                title = item.rating.toString(),
                fontSize = 34,
                color = Color(ShopAppConstants.AppPrimaryTextColor),
                fontFamily = FontFamily.Cursive
            )
            SpacerCommon()

            StarRating(rating = item.rating)
            SpacerCommon()
            //Review

        }

        Reviews(item.reviews)

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Reviews(reviews: List<Review>) {


    reviews.forEachIndexed { _,  item ->
        Column(
            modifier = Modifier
                .padding(10.dp)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            CommonRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
               CommonRow(modifier = Modifier) {
                   Avatar(name = item.reviewerName)
                   Column(modifier = Modifier.padding(horizontal = 4.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                       AppTxt(text = item.reviewerName, textColor = Color.Black)
                   }
               }

                Row {
                    AppTxt(text = parseData(item.date), textColor = Color(ShopAppConstants.AppSecondaryTextColor))
                }

            }


            Column(modifier = Modifier.padding(horizontal = 60.dp)) {

                DescriptionText(title = item.comment, color = Color.Black, fontSize = 16)
            }


        }
    }





    
}



@Composable
fun ProductInformation(item: Product) {



    Column(
        modifier = Modifier
            .padding(10.dp)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        SectionTitleTxt(title = "Product Information")
        Spacer(modifier = Modifier.height(10.dp))


        AppCard(enableDefaultPadding = false, shape = RoundedCornerShape(0.dp)) {
            Column(modifier = Modifier
                .safeContentPadding()
                .padding(3.dp)) {


                `Col-2-row`(
                    row1Space = 0.5f,
                    row2Space = 0.5f,
                    row1 =  {
                        ProductTextHeader(title = "WarrantyInformation : ")
                        ProductText(title = item.warrantyInformation)


                    },
                    row2 =  {
                        ProductTextHeader(title = "Brand ")
                        ProductText(title = item.brand ?:"",)
                    }
                )


                `Col-2-row`(
                    row1Space = 0.5f,
                    row2Space = 0.5f,
                    row1 =  {
                        ProductTextHeader(title = "ShippingInformation : ")
                        ProductText(title = item.shippingInformation,)
                    },
                    row2 =  {
                        ProductTextHeader(title = "Stock : ")
                        ProductText(title = item.stock.toString(),)
                    }
                )

                `Col-2-row`(
                    row1Space = 0.5f,
                    row2Space = 0.5f,
                    row1 =  {
                        ProductTextHeader(title = "AvailabilityStatus : ")
                        ProductText(title = item.availabilityStatus,)
                    },
                    row2 =  {
                        ProductTextHeader(title = "Sku")
                        ProductText(title = item.sku .toString(),)
                    }
                )

                `Col-2-row`(
                    row1Space = 0.5f,
                    row2Space = 0.5f,
                    row1 =  {
                        ProductTextHeader(title = "Return Policy : ")
                        ProductText(title = item.returnPolicy,)
                    },
                    row2 =  {
                        ProductTextHeader(title = "Rating")
                        ProductText(title = item.rating .toString(),)
                    }
                )

                `Col-2-row`(
                    row1Space = 0.5f,
                    row2Space = 0.5f,
                    row1 =  {
                        ProductTextHeader(title = "Minimum Order Quantity : ")
                        ProductText(title = item.minimumOrderQuantity.toString(),)
                    },
                    row2 =  {
                        ProductTextHeader(title = "Weight")
                        ProductText(title = item.weight .toString(),)
                    }
                )

                SectionTitleTxt(title = "Dimensions:", modifier = Modifier.padding(horizontal = 4.dp), color = Color.White, fontSize = 10)

                `Col-2-row`(
                    row1Space = 0.5f,
                    row2Space = 0.5f,
                    row1 =  {


                        ProductTextHeader(title = "Width : ")

                        ProductText(title = item.dimensions.width.toString(),)
                    },
                    row2 =  {
                        ProductTextHeader(title = "Height : ")

                        ProductText(title = item.dimensions.height.toString(),)
                    }
                )


            }

        }
    }


    
}


@Composable
fun AddToCartBottomBar(
    navController: NavController,
    cartViewModel: CartViewModel,
    product: List<Product>?,
    commonViewModel: CommonViewModel,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current

    val counter  =cartViewModel.counter.value
    val cartList  by remember { mutableStateOf(commonViewModel.addToCartItems) }
    val alert by commonViewModel.confirmationAlert.observeAsState()
    val scope = rememberCoroutineScope()

   val showAddToCartSnackbar  : () -> Unit = {

       scope.launch {
           val result = snackbarHostState.showSnackbar(
               message = "Item added to cart",
               actionLabel = "Go to cart",
               duration = SnackbarDuration.Short,
           )

           when(result) {
               SnackbarResult.ActionPerformed -> {
                   navController.navigate(Screen.AdminScreen.route)
                   commonViewModel.changeActiveTab(4)
               }
               SnackbarResult.Dismissed -> {}
           }
       }




    }
    CommonRow(
        modifier = Modifier
            .background(color = Color(ShopAppConstants.cardLightColor))
            .fillMaxWidth()
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        enableDefaultPadding = true
    ) {

        Row(modifier = Modifier.fillMaxWidth(0.4f), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)
        {
           MinusButton {
               cartViewModel.decrementCounter()
           }


            Text(text = counter.toString(), color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)


            PlusButton {
                product?.let {
                    if (counter < it[0].stock && counter < it[0].minimumOrderQuantity) {
                        cartViewModel.incrementCounter()

                    }
                }
            }
        }



        Row(modifier = Modifier.fillMaxWidth(0.6f)) {

            TxtButton(
                text = "Add to Cart",
                `font-size` = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(9.dp)
            )
            {



                product?.let {
                    commonViewModel.updateConfirmationAlert(true)
                    commonViewModel.addItem(AddToCarProduct(id = it[0].id, quantity = counter, stock = it[0].stock, minimumOrderQuantity = it[0].minimumOrderQuantity))

//



                    cartViewModel.addToCart(
                        payload = AddToCartRequest(
                            userId = 51,
                            products = cartList.toList()
                        )
                    )
                    {sucess ->
                            showAddToCartSnackbar()
                        cartViewModel.clearCounter()
                        Log.d("CartList","$cartList")

                    }
                }


            }
        }



    }




}






@Composable
fun Avatar(
    name:String,
    height : Dp = 70.dp,
    shape :  Shape = CircleShape,
    fontSize : TextUnit = 13.sp,
    fontFamily: FontFamily = FontFamily.Monospace,
    fontWeight: FontWeight = FontWeight.SemiBold,
    color: Color = Color(ShopAppConstants.AppIconColor),
    modifier: Modifier = Modifier,
){

   Box(modifier = modifier
       .size(height)
       .clip(shape = shape)
       .background(color = Color(ShopAppConstants.AppPrimaryColor)),
       contentAlignment = Alignment.Center
   ) {
       Text(
           text = name.take(1), 
           fontSize = fontSize,
           fontFamily = fontFamily,
           fontWeight = fontWeight,
           color = color
       )
   }



}
