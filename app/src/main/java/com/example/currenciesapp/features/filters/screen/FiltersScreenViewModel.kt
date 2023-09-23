package com.example.currenciesapp.features.filters.screen

import androidx.lifecycle.SavedStateHandle
import com.example.currenciesapp.features.filters.model.FilterType
import com.example.currenciesapp.features.filters.screen.navigation.FilterPayload
import com.example.navigation.NavigationProvider
import com.example.uikit.base.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiltersScreenViewModel @Inject constructor(
    navigationProvider: NavigationProvider,
    override val savedStateHandle: SavedStateHandle
) : BaseViewModel<FiltersScreenViewModel.UIState>(navigationProvider) {

    init {
        getPayload<FilterPayload>(FilterPayload.PAYLOAD_KEY) {
            onUIState(
                uiState.value.copy(filterType = it.filterType)
            )
        }
    }

    override fun onUIEvent(uiEvent: BaseUIEvent) {
        when (uiEvent) {
            is UIEvent.OnSelectionChange -> reduce(uiEvent)
            is UIEvent.OnApplyClick -> reduce(uiEvent)
            else -> super.onUIEvent(uiEvent)
        }
    }

    private fun reduce(uiEvent: UIEvent.OnApplyClick) {
        goBack(
            FilterPayload(
                uiState.value.filterType
            )
        )
    }

    private fun reduce(uiEvent: UIEvent.OnSelectionChange) {
        onUIState(uiState.value.copy(filterType = uiEvent.filterType))
    }

    data class UIState(
        val filterType: FilterType = FilterType.NAME
    ) : BaseUIState

    sealed class UIEvent : BaseUIEvent {
        object OnApplyClick : UIEvent()
        data class OnSelectionChange(val filterType: FilterType) : UIEvent()
    }

    override fun getStartUIState(): UIState = UIState()
}
