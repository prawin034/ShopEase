package com.example.shopease.feature_admin.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopease.feature_admin.ui.screens.all.details.DetailsScreen
import com.example.shopease.feature_admin.ui.screens.main.AdminScreen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel
import com.example.shopease.feature_login.ui.screens.landingScreeen.LandingScreen
import com.example.shopease.feature_login.ui.screens.loginScreen.LoginScreen
import com.example.shopease.feature_login.ui.viewModel.loginViewModel.LoginViewModel


/* Navigation
This is basically the navigation for all screens
*/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(
    commonViewModel: CommonViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    loginViewModel: LoginViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AdminScreen.route
    ) {

        composable(
            route = Screen.AdminScreen.route,
        ) {
            AdminScreen(navController,commonViewModel,homeScreenViewModel)
        }

        composable(
            route = "${Screen.DetailsScreen.route}/{id}",
            arguments = listOf(
                navArgument("id") {type = NavType.IntType}
            ),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetailsScreen(navController,homeScreenViewModel,id)
        }


        composable(route = Screen.LandingScreen.route,
        ) {
            LandingScreen(navController)
        }

        composable(route = Screen.LoginScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            LoginScreen(navController,loginViewModel)
        }


    } // End of navhost




} // -> End