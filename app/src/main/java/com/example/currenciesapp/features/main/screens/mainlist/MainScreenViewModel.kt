package com.example.currenciesapp.features.main.screens.mainlist

import androidx.lifecycle.SavedStateHandle
import com.example.currenciesapp.core.domain.models.*
import com.example.currenciesapp.core.domain.usecases.*
import com.example.currenciesapp.core.utils.track
import com.example.currenciesapp.features.filters.model.FilterType
import com.example.currenciesapp.features.filters.screen.navigation.FilterPayload
import com.example.currenciesapp.features.filters.screen.navigation.FiltersRoute
import com.example.currenciesapp.features.main.model.*
import com.example.currenciesapp.features.main.utills.sortBy
import com.example.navigation.NavigationProvider
import com.example.remote.model.Currency
import com.example.uikit.base.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    navigationProvider: NavigationProvider,
    private val getFavoriteRatesUseCase: GetFavoriteRatesUseCase,
    private val getLatestRatesUseCase: GetLatestRatesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesRatesUseCase,
    private val deleteFavoriteRateUseCase: DeleteFavoriteRateUseCase,
    override val savedStateHandle: SavedStateHandle
) : BaseViewModel<MainScreenViewModel.UIState>(navigationProvider) {
    private var favorites: List<FavoriteRate> = emptyList()
    private var rates: List<Rate> = emptyList()

    init {
        loadLatestRates()
    }

    override fun onUIEvent(uiEvent: BaseUIEvent) {
        when (uiEvent) {
            is UIEvent.OnCurrencyChanged -> reduce(uiEvent)
            is UIEvent.OnFavoriteClicked -> reduce(uiEvent)
            is UIEvent.OnFiltersClicked -> reduce(uiEvent)
            is LifecycleUIEvent.OnResume -> reduce(uiEvent)
            else -> super.onUIEvent(uiEvent)
        }
    }

    private fun reduce(uiEvent: UIEvent.OnFiltersClicked) {
        openDestination(
            FiltersRoute.Entry.OpenWithPayload(
                uiState.value.filterType
            )
        )
    }

    private fun reduce(uiEvent: LifecycleUIEvent.OnResume) {
        super.onUIEvent(uiEvent)
        if (rates.isNotEmpty()) {
            launchWithCatch({}) {
                favorites = getFavoriteRatesUseCase()

                onUIState(
                    uiState.value.copy(
                        rates = rates.map {
                            it.toUI(
                                favorites.contains(
                                    it.toFavorite(fromCurrency = uiState.value.selectedCurrency)
                                )
                            )
                        }.sortBy(uiState.value.filterType)
                    )
                )
            }
        }

        getPayload<FilterPayload>(FilterPayload.PAYLOAD_KEY) { payload ->
            onUIState(
                uiState.value.copy(
                    filterType = payload.filterType,
                    rates = rates.map {
                        it.toUI(
                            favorites.contains(
                                it.toFavorite(fromCurrency = uiState.value.selectedCurrency)
                            )
                        )
                    }.sortBy(payload.filterType)

                )
            )
        }
    }

    private fun reduce(uiEvent: UIEvent.OnFavoriteClicked) {
        val rates = uiState.value.rates
        launchWithCatch({}) {

            if (uiEvent.rate.isFavorite) {
                deleteFavoriteRateUseCase(
                    uiEvent.rate.toCurrency,
                    uiState.value.selectedCurrency
                )
            } else {
                addToFavoritesUseCase(
                    uiEvent.rate.toRateDomain(),
                    uiState.value.selectedCurrency
                )
            }

            onUIState(
                uiState.value.copy(
                    rates = rates
                        .map {
                            if (it == uiEvent.rate)
                                it.copy(isFavorite = !it.isFavorite)
                            else it
                        }.sortBy(uiState.value.filterType)
                )
            )
        }
    }

    private fun reduce(uiEvent: UIEvent.OnCurrencyChanged) {
        onUIState(
            uiState.value.copy(
                selectedCurrency = uiEvent.currency
            )
        )
        loadLatestRates()
    }

    private fun loadLatestRates() {
        launchWithCatch({
            //Handle Errors
        }) {
            rates = getLatestRatesUseCase.invoke(uiState.value.selectedCurrency)
                .toMutableList().apply {
                    removeIf {
                        it.currency == uiState.value.selectedCurrency
                    }
                }
            favorites = getFavoriteRatesUseCase()

            onUIState(
                uiState.value.copy(
                    rates = rates.map {
                        it.toUI(
                            favorites.contains(
                                it.toFavorite(fromCurrency = uiState.value.selectedCurrency)
                            )
                        )
                    }.sortBy(uiState.value.filterType)
                )
            )
        }.track { onUIState(uiState.value.copy(isLoading = it)) }
    }

    data class UIState(
        val rates: List<RateUI> = emptyList(),
        val selectedCurrency: Currency = Currency.USD,
        val isLoading: Boolean = true,
        val filterType: FilterType = FilterType.NAME
    ) : BaseUIState

    sealed class UIEvent : BaseUIEvent {
        object OnFiltersClicked : UIEvent()
        data class OnCurrencyChanged(val currency: Currency) : UIEvent()
        data class OnFavoriteClicked(val rate: RateUI) : UIEvent()
    }

    override fun getStartUIState(): UIState = UIState()
}