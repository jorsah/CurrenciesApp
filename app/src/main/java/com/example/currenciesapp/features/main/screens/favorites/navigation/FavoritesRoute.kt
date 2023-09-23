package com.example.currenciesapp.features.main.screens.favorites.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.currenciesapp.features.main.screens.favorites.FavoritesScreen
import com.example.navigation.NavigationConfiguration
import com.example.navigation.NavigationDestination
import com.example.navigation.NavigationRoute
import com.example.currenciesapp.features.main.screens.mainlist.MainScreen

object FavoritesRoute : NavigationRoute.ScreenRoute() {

    override fun endpoint(): String = "favorites"

    @Composable
    override fun View(navController: NavController) {
        FavoritesScreen(navController)
    }

    sealed class Entry : NavigationDestination {
        object Single : Entry() {
            override fun getRoute(): NavigationRoute = FavoritesRoute
            override fun getConfiguration(): NavigationConfiguration = NavigationConfiguration(
                popUpTo = FavoritesRoute,
                inclusive = true
            )
        }
    }
}
