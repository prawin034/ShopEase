package com.example.shopease.feature_admin.ui.navigation



/* AppScreens names

 */

sealed class Screen(val route : String) {

      data object AdminScreen : Screen(route = "AdminScreen")
      data object ProfileScreen : Screen("ProfileScreen")


}