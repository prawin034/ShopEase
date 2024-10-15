package com.example.shopease.feature_admin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopease.feature_admin.ui.screens.main.AdminScreen


/* Navigation
This is basically the navigation for all screens
*/
@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AdminScreen.route
    ) {

        composable(
            route = Screen.AdminScreen.route,
        ) {
            AdminScreen()
        }



    } // End of navhost




} // -> End