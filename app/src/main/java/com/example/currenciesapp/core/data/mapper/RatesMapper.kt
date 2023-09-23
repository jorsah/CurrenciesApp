package com.example.currenciesapp.core.data.mapper

import com.example.currenciesapp.core.domain.models.Rate
import com.example.localdata.model.RateEntity
import com.example.remote.model.Currency

fun Rate.toEntity(fromCurrency: Currency) = RateEntity(
    id = "${fromCurrency.name}/${currency.name}/$value",
    from = fromCurrency.toEntity(),
    to = currency.toEntity(),
    value = value
)