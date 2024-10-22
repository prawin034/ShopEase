package com.example.shopease.feature_login.ui.screens.resgistrationScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_common.components.AppScaffold
import com.example.shopease.feature_common.components.AppTxt
import com.example.shopease.feature_common.components.BackIconButton
import com.example.shopease.feature_common.components.CmnTxtField
import com.example.shopease.feature_common.components.CustomTopAppBar
import com.example.shopease.feature_common.components.PrimaryActionBtn
import com.example.shopease.feature_common.components.SectionTitleTxt
import com.example.shopease.feature_common.components.SpacerCommon
import com.example.shopease.feature_common.components.TxtButton
import com.example.shopease.feature_common.components.iconBtn
import com.example.shopease.feature_common.utils.ShopAppConstants
import com.example.shopease.feature_login.model.RegisterRequest
import com.example.shopease.feature_login.model.loginRequest
import com.example.shopease.feature_login.ui.viewModel.registrationViewModel.RegistrationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController,registrationViewModel: RegistrationViewModel){


    val firstName by registrationViewModel.firstName.observeAsState("")
    val lastName by registrationViewModel.lastName.observeAsState("")
    val email by registrationViewModel.email.observeAsState("")
    val password by registrationViewModel.password.observeAsState("")
    val isPasswordVisible  = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val successMessage by registrationViewModel.successMessage.observeAsState()
    val failureMessage by registrationViewModel.failureMessage.observeAsState()
    AppScaffold(
        topAppBar = {
            CustomTopAppBar(
                navigationIcon = {
                    BackIconButton{

                    }
                },
                titleContent = { /*TODO*/ }
            ) {

            }
        },
        bottomAppBar = { /*TODO*/ },
        floatingButton = { /*TODO*/ })
    { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(it)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {


            SectionTitleTxt(
                title = "Create New Account".uppercase(),
                fontSize = 14,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )

            SpacerCommon()
            SpacerCommon()
            SpacerCommon()
            SpacerCommon()
            SpacerCommon()
            AppTxt(
                text = "FirstName".uppercase(),
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )


            CmnTxtField(
                value = firstName,
                label = {
                },
                leadingIcon = {},
                trailingIcon = { /*TODO*/ },
                placeHolder = {
                    AppTxt(
                        text = "UserName",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Cursive,
                        textColor = Color.Black
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(1f),
                visualTransformation = VisualTransformation.None,
                onValueChange = {firstName ->
                   registrationViewModel.updateFirstName(firstName)
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.SansSerif
                )
            )


            SpacerCommon()
            SpacerCommon()
            SpacerCommon()


            AppTxt(
                text = "LastName".uppercase(),
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            CmnTxtField(
                value = lastName,
                label = {
                },
                leadingIcon = {},
                trailingIcon =  {},
                placeHolder = {
                    AppTxt(
                        text = "LastName",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif,
                        textColor = Color.Black
                    )
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(1f),
                onValueChange = {
                    registrationViewModel.updateLastName(it)
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.SansSerif
                ),
                visualTransformation = VisualTransformation.None
            )


            SpacerCommon()
            SpacerCommon()
            SpacerCommon()


            AppTxt(
                text = "Email".uppercase(),
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            CmnTxtField(
                value = email,
                label = {
                },
                leadingIcon = {},
                trailingIcon =  {},
                placeHolder = {
                    AppTxt(
                        text = "example@gmail.com",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif,
                        textColor = Color.Black
                    )
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(1f),
                onValueChange = {
                    registrationViewModel.updateEmail(it)
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.SansSerif
                ),
                visualTransformation = VisualTransformation.None
            )

            SpacerCommon()
            SpacerCommon()
            SpacerCommon()


            AppTxt(
                text = "Password".uppercase(),
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            CmnTxtField(
                value = password,
                label = {
                },
                leadingIcon = {},
                trailingIcon =  {
                      IconButton(onClick = {
                          isPasswordVisible.value = !isPasswordVisible.value
                      }) {
                          Icon(imageVector = if(isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = "", tint = Color.Black)
                      }
                },
                placeHolder = {
                    AppTxt(
                        text = "",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif,
                        textColor = Color.Black
                    )
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(1f),
                onValueChange = {
                    registrationViewModel.updatePassword(it)
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.SansSerif
                ),
                visualTransformation = if(isPasswordVisible.value)  VisualTransformation.None else PasswordVisualTransformation()
            )


            SpacerCommon()
            SpacerCommon()
            SpacerCommon()
            PrimaryActionBtn(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(58.dp)
                    .padding(bottom = 10.dp),
                text = "Register".uppercase(),
                fontSize = 14.5.sp,
                fontFamily = FontFamily.SansSerif,
                shape = RoundedCornerShape(11.dp),

                )
            {

                registrationViewModel.register(context,
                    payload = RegisterRequest(
                        username = firstName,
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password,
                    )
                ) {success ->
                    if(success) {
                        navController.navigate(Screen.LoginScreen.route)
                    }
                }

            }




            SpacerCommon()
            SpacerCommon()

            TxtButton(
                text = "have account? : SignIn",
                `font-size` = 13.sp,
                textColor = Color.Black,
                backgroundColor = Color.White
            )
            {
                navController.navigate(Screen.LoginScreen.route)
            }


        }
    }



    if(successMessage !=null){
        Toast.makeText(context,"$successMessage", Toast.LENGTH_SHORT).show()
    }
    if(failureMessage !=null) {
        Toast.makeText(context,"$failureMessage", Toast.LENGTH_SHORT).show()

    }




}