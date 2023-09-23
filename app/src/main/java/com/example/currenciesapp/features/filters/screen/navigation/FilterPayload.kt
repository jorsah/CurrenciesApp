package com.example.currenciesapp.features.filters.screen.navigation

import com.example.currenciesapp.features.filters.model.FilterType
import com.example.navigation.NavigationPayload
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterPayload(
    val filterType: FilterType,
    override val payloadKey: String = PAYLOAD_KEY
) : NavigationPayload {
    companion object {
        const val PAYLOAD_KEY = "filter_key"
    }
}
