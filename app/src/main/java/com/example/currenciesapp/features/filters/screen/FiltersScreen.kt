package com.example.currenciesapp.features.filters.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.currenciesapp.features.filters.model.FilterType
import com.example.uikit.base.*
import com.example.uikit.style.StyledKit
import com.example.uikit.style.TextStyle.defaultSmall
import com.example.uikit.utills.SpacerFill
import com.example.uikit.utills.Strings

@Composable
fun FiltersScreen(navController: NavController) {
    val viewModel = hiltViewModel<FiltersScreenViewModel>()

    BaseScreen(viewModel = viewModel, navController = navController) {
        InternalFiltersScreen(
            uiState = viewModel.uiState.value,
            onUIEvent = viewModel::onUIEvent
        )
    }
}

@Composable
private fun InternalFiltersScreen(
    uiState: FiltersScreenViewModel.UIState,
    onUIEvent: (BaseUIEvent) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StyledKit.TopAppBar(
            title = stringResource(id = Strings.filter_screen_title),
            hasBackButton = true,
            onBackButtonClick = {
                onUIEvent(BackUIEvent)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = Strings.filter_screen_sort), style = defaultSmall)

            FilterType.values().forEach {
                StyledKit.RadioItem(
                    modifier = Modifier.padding(vertical = 16.dp),
                    title = stringResource(id = it.nameRes),
                    isSelected = it == uiState.filterType,
                    onClick = {
                        onUIEvent(FiltersScreenViewModel.UIEvent.OnSelectionChange(it))
                    }
                )
            }

            SpacerFill()

            StyledKit.Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                text = stringResource(id = Strings.filter_apply_btn)
            ) {
                onUIEvent(FiltersScreenViewModel.UIEvent.OnApplyClick)
            }
        }
    }
}
