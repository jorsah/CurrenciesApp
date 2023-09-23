package com.example.currenciesapp.features.main.model

import com.example.currenciesapp.core.domain.models.FavoriteRate
import com.example.currenciesapp.core.domain.models.Rate
import com.example.remote.model.Currency

data class RateUI(
    val toCurrency: Currency,
    val value: Double,
    val fromCurrency: Currency? = null,
    val isFavorite: Boolean = false
)

fun FavoriteRate.toUI() = RateUI(
    toCurrency = toCurrency,
    fromCurrency = fromCurrency,
    value = value,
    isFavorite = true
)

fun Rate.toUI(isFavorite: Boolean = false) = RateUI(
    toCurrency = currency,
    value = value,
    isFavorite = isFavorite
)

fun RateUI.toRateDomain() = Rate(
    currency = toCurrency,
    value = value
)

fun RateUI.toFavoriteDomain() = FavoriteRate(
    toCurrency = toCurrency,
    value = value,
    fromCurrency = fromCurrency ?: Currency.USD,
)
