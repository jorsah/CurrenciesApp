package com.example.currenciesapp.features.main.screens.mainlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.navigation.NavigationConfiguration
import com.example.navigation.NavigationDestination
import com.example.navigation.NavigationRoute
import com.example.currenciesapp.features.main.screens.mainlist.MainScreen

object MainRoute : NavigationRoute.ScreenRoute() {

    override fun endpoint(): String = "main"

    @Composable
    override fun View(navController: NavController) {
        MainScreen(navController)
    }

    sealed class Entry : NavigationDestination {
        object Single : Entry() {
            override fun getRoute(): NavigationRoute = MainRoute
            override fun getConfiguration(): NavigationConfiguration = NavigationConfiguration(
                popUpTo = MainRoute,
                inclusive = true
            )
        }
    }
}
