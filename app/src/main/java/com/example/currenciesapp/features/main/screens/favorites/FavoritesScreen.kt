package com.example.currenciesapp.features.main.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.currenciesapp.features.main.ui.CurrencyCard
import com.example.uikit.base.*
import com.example.uikit.style.StyledKit
import com.example.uikit.utills.SpacerHeight

@Composable
fun FavoritesScreen(navController: NavController) {
    val viewModel = hiltViewModel<FavoritesScreenViewModel>()

    BaseScreen(viewModel = viewModel, navController = navController) {
        InternalFavoritesScreen(
            uiState = viewModel.uiState.value,
            onUIEvent = viewModel::onUIEvent
        )
    }
}

@Composable
private fun InternalFavoritesScreen(
    uiState: FavoritesScreenViewModel.UIState,
    onUIEvent: (BaseUIEvent) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StyledKit.TopAppBar(title = "Favorites")

        Column(modifier = Modifier.padding(16.dp)) {
            uiState.rates.forEach {
                CurrencyCard(rate = it) {
                    onUIEvent(FavoritesScreenViewModel.UIEvent.OnFavoriteClicked(it))
                }

                SpacerHeight(height = 8.dp)
            }
        }
    }
}
