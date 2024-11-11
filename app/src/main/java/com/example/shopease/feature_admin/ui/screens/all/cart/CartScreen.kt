package com.example.shopease.feature_admin.ui.screens.all.cart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shopease.feature_admin.data.model.AddToCarProduct
import com.example.shopease.feature_admin.data.model.AddToCartRequest
import com.example.shopease.feature_admin.data.model.AddToCartResponse
import com.example.shopease.feature_admin.data.model.CartResponseList
import com.example.shopease.feature_admin.data.model.UpdateCartRequest
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.cart.CartViewModel
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.MinusButton
import com.example.shopease.feature_common.components.PlusButton
import com.example.shopease.feature_common.components.PriceText
import com.example.shopease.feature_common.components.PrimaryActionBtn
import com.example.shopease.feature_common.components.SpacerCommon
import com.example.shopease.feature_common.components.iconBtn
import com.example.shopease.feature_common.utils.ShopAppConstants
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController,cartViewModel: CartViewModel,commonViewModel: CommonViewModel){

    val cartResponse by cartViewModel.cartList.observeAsState(emptyList())
    AppScaffold(
        topAppBar = {

                 CustomTopAppBar(navigationIcon = {

                 }, titleContent = {
                     AppTxt(text = "Cart")
                 }) {
                     
                 }   
        },
        bottomAppBar = {
        },
        floatingButton = { /*TODO*/ }) { it ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp)
                .padding(bottom = 100.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            if (cartResponse?.isNotEmpty() == true) {


                CommonRow(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .padding(7.dp)
                )
                {
                    AppTxt(
                        text = "Deliver to".capitalize(Locale.ENGLISH),
                        textColor = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold
                    )


                    AppTxt(
                        fontSize = 11.sp,
                        text = "5,Abhirami garden near ".capitalize(Locale.ENGLISH),
                        textColor = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 4
                    )
                }

                SpacerCommon()

                Divider(color = Color.LightGray, thickness = 2.dp)

                if (cartResponse?.isNotEmpty() == true) {
                    cartResponse?.let { cartList ->
                        CartItems(product = cartList[0], cartViewModel,commonViewModel)
                    }
                }


                if (cartResponse?.isNotEmpty() == true) {
                    cartResponse?.let { cartList ->
                        ProductCostInformation(cart = cartList[0])
                    }
                }


                //checkout

                PrimaryActionBtn(
                    text = "Checkout", textColor = Color.White, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(58.dp)
                        .padding(4.dp)
                )
                {

                }


            }
            else {
                SpacerCommon()
                SpacerCommon()
                SpacerCommon()
                iconBtn(
                    icon = Icons.Default.ShoppingCartCheckout,
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0x74A8A0A0)),
                    modifier = Modifier.size(200.dp),
                    iconColor = Color.White,
                    iconModifier = Modifier.size(120.dp)
                ) {
                }

                SpacerCommon()
                SpacerCommon()
                AppTxt(text = "Your cart is empty", fontSize = 18.sp, fontFamily = FontFamily.Monospace)
                SpacerCommon()
                SpacerCommon()
                AppTxt(text = "Looks like you have not added anything to cart. \n Go ahead and add Categories", fontSize = 12.sp , maxLines = 3, textAlign = TextAlign.Center)
            }

        }
    }
}



@Composable
fun CartItems(product:AddToCartResponse,cartViewModel: CartViewModel,commonViewModel: CommonViewModel) {


   val successMesasge  by  cartViewModel.successMessage.observeAsState("")
    val failureMsg  by cartViewModel.failureMessage.observeAsState("")
    val context = LocalContext.current
    val cartItem = commonViewModel.addToCartItems
    Column {
        product.products.forEachIndexed { index: Int, item: CartResponseList ->
            CommonRow(
                enableDefaultPadding = false, modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(1f)
            )
            {
                CommonRow(modifier = Modifier.weight(1f)) {

                    AsyncImageComponent(
                        imageUrl = item.thumbnail,
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.FillWidth,
                        width = 100.dp,
                        height = 100.dp
                    )

                    Column(
                        modifier = Modifier.padding(3.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        AppTxt(
                            text = item.title,
                            fontSize = 8.sp,
                            maxLines = 10,
                            letterSpacing = 1.sp
                        )
                        PriceText(
                            fontSize = 13, title = "$${item.price}", color = Color(
                                ShopAppConstants.AppPrimaryColor
                            )
                        )

                    }


                }



                CommonRow(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    MinusButton {
                        var counter = item.quantity
                        --counter
                            cartViewModel.addToCart(
                                payload = AddToCartRequest(
                                    userId = 51,
                                    products =  listOf(
                                        AddToCarProduct(
                                            id = item.id,
                                            quantity = counter,
                                            stock = 0,
                                            minimumOrderQuantity = 0
                                        ),
                                    )
                                )
                            )
                            {sucess ->
//                                navController.navigate(Screen.AdminScreen.route)
//                                commonViewModel.changeActiveTab(3)
//                                cartViewModel.clearCounter()

                            }


                    }


                    Text(
                        text = item.quantity.toString(),
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )


                    PlusButton {
                        var counter = item.quantity
                        val myItem = cartItem.filter { it.id == item.id}
//                        if(counter < myItem[0].stock && counter <myItem.get(0).minimumOrderQuantity) ++counter
//                            cartViewModel.addToCart(
//                                payload = AddToCartRequest(
//                                    userId = 51,
//                                    products =  listOf(
//                                        AddToCarProduct(
//                                            id = item.id,
//                                            quantity = counter,
//                                            stock = 0,
//                                            minimumOrderQuantity = 0
//                                        ),
//                                    )
//                                )
//                            )
//                            {sucess ->
//                            }

                    }
                }


            }



        }
    }



//    if(successMesasge !=null) {
//        Toast.makeText(context,"$successMesasge",Toast.LENGTH_SHORT).show()
//        cartViewModel.clearToast()
//    }
//
//    if(failureMsg !=null) {
//        Toast.makeText(context,"$failureMsg",Toast.LENGTH_SHORT).show()
//        cartViewModel.clearToast()
//    }




}

@Composable
fun ProductCostInformation(cart: AddToCartResponse) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Display each product in the cart
        cart.products.forEach { product ->
            CommonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                // Display product title
                AppTxt(text = product.title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))

                // Display price * quantity
                AppTxt(text = "$${product.price}")
                AppTxt(text = "x${product.quantity}", modifier = Modifier.padding(end = 8.dp))

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subtotal
        CommonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            AppTxt(text = "Subtotal:", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            AppTxt(text = "$${cart.total}")
        }

        // Total Products
        CommonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            AppTxt(text = "Total Products:", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            AppTxt(text = "${cart.totalProducts}")
        }

        // Total Quantity
        CommonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            AppTxt(text = "Total Quantity:", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            AppTxt(text = "${cart.totalQuantity}")
        }

        // Total Discount
        CommonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            AppTxt(text = "Total Discount:", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            AppTxt(text = "-$${cart.total - cart.discountedTotal}")
        }

        // Total After Discount
        CommonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            AppTxt(text = "Total (after discount):", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            AppTxt(text = "$${cart.discountedTotal.toInt()}")
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}
