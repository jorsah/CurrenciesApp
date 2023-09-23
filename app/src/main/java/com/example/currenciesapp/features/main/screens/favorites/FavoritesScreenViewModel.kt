package com.example.currenciesapp.features.main.screens.favorites

import androidx.lifecycle.SavedStateHandle
import com.example.currenciesapp.core.domain.usecases.DeleteFavoriteRateUseCase
import com.example.currenciesapp.core.domain.usecases.GetFavoriteRatesUseCase
import com.example.currenciesapp.features.main.model.*
import com.example.navigation.NavigationProvider
import com.example.uikit.base.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val getFavoriteRatesUseCase: GetFavoriteRatesUseCase,
    private val deleteFavoriteRateUseCase: DeleteFavoriteRateUseCase,
    navigationProvider: NavigationProvider,
    override val savedStateHandle: SavedStateHandle
) : BaseViewModel<FavoritesScreenViewModel.UIState>(navigationProvider) {

    init {
        launchWithCatch({}) {
            val rates = getFavoriteRatesUseCase()

            onUIState(
                uiState.value.copy(
                    rates = rates.map { it.toUI() }
                )
            )
        }
    }

    override fun onUIEvent(uiEvent: BaseUIEvent) {
        when (uiEvent) {
            is UIEvent.OnFavoriteClicked -> reduce(uiEvent)
            else -> super.onUIEvent(uiEvent)
        }
    }

    private fun reduce(uiEvent: UIEvent.OnFavoriteClicked) {
        val rates = uiState.value.rates

        launchWithCatch({}) {
            deleteFavoriteRateUseCase(uiEvent.rate.toFavoriteDomain())

            onUIState(
                uiState.value.copy(
                    rates = rates
                        .toMutableList()
                        .apply { remove(uiEvent.rate) }
                )
            )
        }
    }

    data class UIState(
        val rates: List<RateUI> = emptyList(),
    ) : BaseUIState

    sealed class UIEvent : BaseUIEvent {
        data class OnFavoriteClicked(val rate: RateUI) : UIEvent()
    }

    override fun getStartUIState(): UIState = UIState()
}