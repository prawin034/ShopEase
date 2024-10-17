package com.example.shopease.feature_admin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopease.feature_admin.ui.screens.all.cart.CartScreen
import com.example.shopease.feature_admin.ui.screens.all.home.HomeScreen
import com.example.shopease.feature_admin.ui.screens.all.profile.ProfileScreen
import com.example.shopease.feature_admin.ui.screens.all.search.SearchScreen
import com.example.shopease.feature_admin.ui.screens.main.AdminScreen
import com.example.shopease.feature_admin.ui.viewModel.CommonViewModel
import com.example.shopease.feature_admin.ui.viewModel.home.HomeScreenViewModel


/* Navigation
This is basically the navigation for all screens
*/
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


    } // End of navhost




} // -> End