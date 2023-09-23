package com.example.currenciesapp.core.domain.models

import com.example.currenciesapp.core.data.mapper.toEntity
import com.example.localdata.model.RateEntity
import com.example.remote.model.Currency


data class FavoriteRate(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val value: Double
)

fun RateEntity.toDomain() = FavoriteRate(
    fromCurrency = from.toEntity(),
    toCurrency = to.toEntity(),
    value = value
)

fun Rate.toFavorite(fromCurrency: Currency) =
    FavoriteRate(
        fromCurrency = fromCurrency,
        toCurrency = currency,
        value = value
    )
