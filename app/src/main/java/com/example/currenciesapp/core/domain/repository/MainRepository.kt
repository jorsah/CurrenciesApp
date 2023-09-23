package com.example.currenciesapp.core.domain.repository

import com.example.currenciesapp.core.domain.models.Rate
import com.example.localdata.model.RateEntity
import com.example.remote.model.Currency

interface MainRepository {
    suspend fun getRates(base: Currency): List<Rate>
    suspend fun addToFavorites(rate: Rate, fromCurrency: Currency)
    suspend fun deleteFromFavorites( fromCurrency: Currency,toCurrency: Currency,)
    suspend fun getAllFavorites(): List<RateEntity>
}
