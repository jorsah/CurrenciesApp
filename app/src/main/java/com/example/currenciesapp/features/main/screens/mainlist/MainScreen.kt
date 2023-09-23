package com.example.currenciesapp.features.main.screens.mainlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uikit.utills.Drawables
import com.example.uikit.utills.Strings
import com.example.currenciesapp.features.main.ui.CurrencyCard
import com.example.currenciesapp.features.main.ui.DropdownMenu
import com.example.uikit.base.*
import com.example.uikit.style.Colors
import com.example.uikit.style.StyledKit
import com.example.uikit.utills.*

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainScreenViewModel>()

    BaseScreen(viewModel = viewModel, navController = navController) {
        InternalMainScreen(
            uiState = viewModel.uiState.value,
            onUIEvent = viewModel::onUIEvent
        )
    }
}

@Composable
private fun InternalMainScreen(
    uiState: MainScreenViewModel.UIState,
    onUIEvent: (BaseUIEvent) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StyledKit.TopAppBar(title = stringResource(id = Strings.main_screen_title)) {
            SpacerHeight(height = 8.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                DropdownMenu(
                    selected = uiState.selectedCurrency,
                    currencies = uiState.rates.map { it.toCurrency }
                ) {
                    onUIEvent(MainScreenViewModel.UIEvent.OnCurrencyChanged(it))
                }

                SpacerFill()

                StyledKit.IconButton(
                    iconId = Drawables.ic_filter
                ) {
                    onUIEvent(MainScreenViewModel.UIEvent.OnFiltersClicked)
                }

                SpacerWidth(width = 12.dp)
            }
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Colors.primaryMain)
            }
        } else {
            Column(modifier = Modifier.padding(16.dp)) {
                uiState.rates.forEach {
                    CurrencyCard(rate = it) {
                        onUIEvent(MainScreenViewModel.UIEvent.OnFavoriteClicked(it))
                    }

                    SpacerHeight(height = 8.dp)
                }
            }
        }
    }
}