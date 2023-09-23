package com.example.remote.service

import com.example.remote.model.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CurrenciesService {

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String = defaultSymbols.first(),
        @Query("symbols") symbols: String = defaultSymbols.joinToString()
    ): CurrencyRateResponse

    companion object {
        private val defaultSymbols =
            listOf(Currency.USD.name, Currency.AMD.name, Currency.RUB.name, Currency.EUR.name)
    }
}
