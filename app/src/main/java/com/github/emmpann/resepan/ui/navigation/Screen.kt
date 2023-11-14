package com.github.emmpann.resepan.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object DetailFood : Screen("home/{foodId}") {
        fun createRoute(foodId: Int) = "home/$foodId"
    }
}