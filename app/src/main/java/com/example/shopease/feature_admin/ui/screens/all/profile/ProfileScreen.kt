package com.example.shopease.feature_admin.ui.screens.all.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_common.components.AppCard
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.AsyncImageComponent
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.CartIconBtn
import com.example.shopease.feature_common.components.CommonRow
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.components.SpacerCommon
import com.example.shopease.feature_common.components.iconBtn
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_login.dataStore.getTheme
import com.example.shopease.feature_login.dataStore.saveTheme
import com.example.shopease.feature_login.model.loginResponse
import com.example.shopease.feature_login.ui.viewModel.loginViewModel.LoginViewModel
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController,loginViewModel: LoginViewModel){

    val loginResponse by  loginViewModel.loginResponse.observeAsState(emptyList())


    AppScaffold(
        topAppBar = {
           CustomTopAppBar(
               navigationIcon = {
                     BackIconButton {

                     }
               },
               titleContent = {
                   AppTxt(text = "Profile", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.SansSerif, textColor = Color.Black)
               }
           ) {

           }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    {

        Column(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .fillMaxSize()
                .imePadding()
                .padding(it)
                .padding(10.dp)

            ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        )
        {

            LazyColumn{
              itemsIndexed(loginResponse ?: emptyList()) { index: Int, item: loginResponse ->


                  Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                  AsyncImageComponent(imageUrl = item.image, modifier = Modifier.size(100.dp), shape = CircleShape, color = Color.Transparent)
                  }

                  SpacerCommon()

                  AppTxt(text = item.email.capitalize(Locale.ROOT), fontSize = 8.sp, modifier = Modifier
                      .fillMaxWidth(),
                      textAlign = TextAlign.Center
                  )


                  PersonalInformation(item)
                  AccountSettings(loginData = item)
                  GeneralSettings()
              }

            }

        }


    }

}



@Composable
fun PersonalInformation(item: loginResponse) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center)
    {
        AppTxt(
            text = "Personal Information",
            fontSize = 13.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )
        SpacerCommon()
        AppCard(
            enableDefaultPadding = false,
            modifier = Modifier.fillMaxWidth(),

            ) {
            CommonRow(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppTxt(
                    text = "FirstName",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White,
                )

                AppTxt(
                    text = item.firstName,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White,  textAlign = TextAlign.Start

                )
            }
            Divider()

            CommonRow(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppTxt(
                    text = "LastName",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White
                )

                AppTxt(
                    text = item.lastName,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White,  textAlign = TextAlign.Start
                )
            }


            Divider()


            CommonRow(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppTxt(
                    text = "Gender",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White
                )

                AppTxt(
                    text = item.gender,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White
                )
            }


            Divider()


        }
    }

}


@Composable
fun AccountSettings(loginData: loginResponse) {
    val settings = listOf("Edit Profile","Shopping Address","WishList","Orders","Cards")
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center)
    {
        AppTxt(
            text = "Account Settings",
            fontSize = 13.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )
        SpacerCommon()
        AppCard(
            enableDefaultPadding = false,
            modifier = Modifier.fillMaxWidth(),

            ) {

            settings.forEachIndexed { _, item ->
                CommonRow(
                    modifier = Modifier
                        .clickable {

                        }
                        .padding(0.dp)
                        .fillMaxWidth()
                        .padding(20.dp)

                    ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = mapIconNameProfile(name = item), contentDescription = "", tint = Color.White, modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(15.dp))

                        AppTxt(
                            text = item,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            textColor = Color.White,
                        )
                    }


                    Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "", tint = Color.White,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(15.dp)

                    )

                }
                Divider()
            }




        }
    }
}

@Composable
fun GeneralSettings() {
    val commonViewModel :CommonViewModel = viewModel()
    val context = LocalContext.current
    val appTheme by commonViewModel.isDarkTheme.observeAsState(initial = getTheme(context))
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center)
    {
        AppTxt(
            text = "General",
            fontSize = 13.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            textColor = Color.Black
        )
        SpacerCommon()
        AppCard(
            enableDefaultPadding = false,
            modifier = Modifier.fillMaxWidth(),

            ) {

            CommonRow(
                modifier = Modifier
                    .clickable {

                    }
                    .padding(0.dp)
                    .fillMaxWidth()
                    .padding(20.dp)

                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){

                AppTxt(
                    text = "Dark Mode",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.White,
                )

                Switch(
                    checked = appTheme,
                    onCheckedChange = {
                        commonViewModel.updateTheme(it,context)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor =  Color(0xFFFFFFFF), // Dark thumb for dark mode, white thumb for light mode
                        checkedTrackColor = Color(0xFF4CAF50), // Cyan for dark mode, green for light mode
                        uncheckedThumbColor = Color(0xFF4CAF50), // Light gray for light mode, dark gray for dark mode
                        uncheckedTrackColor =  Color(0xFFFFFFFF)  // Dark gray for dark mode, light gray for light mode
                    )
                )


            }


        }
    }
}



@Composable
fun mapIconNameProfile(name:String) :ImageVector {
    val settings = listOf("Edit Profile","Shopping Address","WishList","Orders","Cards")
    return  when(name.lowercase()) {
          "edit profile" -> Icons.Default.Person
          "shopping address" -> Icons.Default.Home
          "wishlist" -> Icons.Default.Favorite
          "orders" -> Icons.Default.ShoppingCart
          "cards" -> Icons.Default.CreditCard
          else -> Icons.Default.Bookmarks
    }
}