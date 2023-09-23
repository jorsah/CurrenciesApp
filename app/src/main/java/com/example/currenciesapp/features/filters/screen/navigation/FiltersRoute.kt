package com.example.currenciesapp.features.filters.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.currenciesapp.features.filters.model.FilterType
import com.example.currenciesapp.features.filters.screen.FiltersScreen
import com.example.navigation.*

object FiltersRoute : NavigationRoute.ScreenRoute() {

    override fun endpoint(): String = "filters"

    @Composable
    override fun View(navController: NavController) {
        FiltersScreen(navController)
    }

    sealed class Entry : NavigationDestination {

        data class OpenWithPayload(
            private val filterType: FilterType
        ) : Entry() {
            override fun getRoute(): NavigationRoute = FiltersRoute

            override fun getPayload(): NavigationPayload = FilterPayload(filterType)
        }
    }
}
