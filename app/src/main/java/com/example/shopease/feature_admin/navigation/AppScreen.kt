package com.example.shopease.feature_admin.navigation



/* AppScreens names

 */

sealed class Screen(val route : String) {

      data object AdminScreen : Screen( "AdminScreen")
      data object DetailsScreen : Screen("DetailsScreen")

      data object LandingScreen : Screen("LandingScreen")
      data object LoginScreen : Screen("LoginScreen")
      data object RegistrationScreen : Screen("RegistrationScreen")

}