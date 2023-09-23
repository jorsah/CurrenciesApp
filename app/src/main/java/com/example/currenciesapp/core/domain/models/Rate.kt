package com.example.currenciesapp.core.domain.models

import com.example.remote.model.Currency

data class Rate(
    val currency: Currency,
    val value: Double
)
