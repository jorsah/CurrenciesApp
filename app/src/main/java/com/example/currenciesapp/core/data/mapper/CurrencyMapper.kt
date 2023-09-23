package com.example.currenciesapp.core.data.mapper

import com.example.localdata.model.CurrencyEntity
import com.example.remote.model.Currency

fun Currency.toEntity() = when (this) {
    Currency.AMD -> CurrencyEntity.AMD
    Currency.USD -> CurrencyEntity.USD
    Currency.RUB -> CurrencyEntity.RUB
    Currency.EUR -> CurrencyEntity.EUR
}

fun CurrencyEntity.toEntity() = when (this) {
    CurrencyEntity.AMD -> Currency.AMD
    CurrencyEntity.USD -> Currency.USD
    CurrencyEntity.RUB -> Currency.RUB
    CurrencyEntity.EUR -> Currency.EUR
}
