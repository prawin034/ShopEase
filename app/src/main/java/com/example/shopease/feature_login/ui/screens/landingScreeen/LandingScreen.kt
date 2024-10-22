package com.example.shopease.feature_login.ui.screens.landingScreeen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopease.R
import com.example.shopease.feature_admin.navigation.Screen
import com.example.shopease.feature_common.components.PrimaryActionBtn


@Composable
fun LandingScreen(navController: NavController){



    Surface(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(insets = WindowInsets(0.dp))
    ) {
        Box(modifier = Modifier,) {

            Image(
                painter = painterResource(id = R.drawable.model1),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(color = Color.LightGray, blendMode = BlendMode.DstAtop)
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {

           val interactionSource  = remember { MutableInteractionSource() }
            val isPressed = interactionSource.collectIsPressedAsState().value


            PrimaryActionBtn(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(55.dp)
                    .padding(bottom = 10.dp),
                text = "Login".uppercase(),
                fontSize = 14.5.sp,
                fontFamily = FontFamily.SansSerif,
                shape = RoundedCornerShape(11.dp),

            ) {
                navController.navigate(Screen.LoginScreen.route)
            }


            PrimaryActionBtn(
                text = "Registration".uppercase(),
                fontSize = 14.5.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth(0.85f)
                    .height(55.dp)
                    .padding(bottom = 6.dp),
                shape = RoundedCornerShape(11.dp),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = if(isPressed) Color(0xFFB92658) else  Color(0xFFFF7CA9)
                ),
                interactionSource = interactionSource
            ) {

            }



        }
    }




}