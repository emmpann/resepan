package com.github.emmpann.resepan.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.ui.theme.ResepanTheme
import com.github.emmpann.resepan.ui.navigation.NavigationItem
import com.github.emmpann.resepan.ui.navigation.Screen
import com.github.emmpann.resepan.ui.screen.about.AboutScreen
import com.github.emmpann.resepan.ui.screen.detail.DetailScreen
import com.github.emmpann.resepan.ui.screen.favorite.FavoriteScreen
import com.github.emmpann.resepan.ui.screen.home.HomeScreen

@Composable
fun ResepanApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailFood.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { foodId ->
                        navController.navigate(Screen.DetailFood.createRoute(foodId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(navigateToDetail = { foodId ->
                    navController.navigate(Screen.DetailFood.createRoute(foodId))
                })
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailFood.route,
                arguments = listOf(navArgument("foodId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("foodId") ?: -1
                DetailScreen(
                    foodId = id,
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_favortie),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_about),
                icon = Icons.Default.Person,
                screen = Screen.About
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResepanAppPreview() {
    ResepanTheme {
        ResepanApp()
    }
}