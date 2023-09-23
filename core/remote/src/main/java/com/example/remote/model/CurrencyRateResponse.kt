package com.example.remote.model

data class CurrencyRateResponse(
    val success: Boolean,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
