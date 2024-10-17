package com.example.shopease.feature_admin.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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


/* Navigation
This is basically the navigation for all screens
*/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(
    commonViewModel: CommonViewModel,
    homeScreenViewModel: HomeScreenViewModel
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
            )
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetailsScreen(navController,homeScreenViewModel,id)
        }


    } // End of navhost




} // -> End