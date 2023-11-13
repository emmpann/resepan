package com.github.emmpann.resepan.ui.theme.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("cart")
    object About : Screen("about")
    object DetailFood : Screen("home/{rewardId}") {
        fun createRoute(foodId: Long) = "home/$foodId"
    }
}