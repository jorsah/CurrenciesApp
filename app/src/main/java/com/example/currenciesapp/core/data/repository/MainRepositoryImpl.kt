package com.example.currenciesapp.core.data.repository

import com.example.currenciesapp.core.data.mapper.toEntity
import com.example.currenciesapp.core.domain.models.Rate
import com.example.currenciesapp.core.domain.repository.MainRepository
import com.example.localdata.dao.RateDao
import com.example.localdata.model.RateEntity
import com.example.remote.model.Currency
import com.example.remote.service.CurrenciesService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: CurrenciesService,
    private val dao: RateDao
) : MainRepository {

    override suspend fun getRates(base: Currency): List<Rate> =
        service.getRates(base = base.name).rates.map {
            val currency = when (it.key) {
                Currency.AMD.name -> Currency.AMD
                Currency.EUR.name -> Currency.EUR
                Currency.RUB.name -> Currency.RUB
                else -> Currency.USD
            }
            Rate(currency, it.value)
        }

    override suspend fun addToFavorites(rate: Rate, fromCurrency: Currency) {
        dao.insertRate(rate.toEntity(fromCurrency))
    }

    override suspend fun deleteFromFavorites(fromCurrency: Currency, toCurrency: Currency,) {
        dao.deleteRate(fromCurrency.toEntity(), toCurrency.toEntity())
    }

    override suspend fun getAllFavorites(): List<RateEntity> = dao.getRates()

}
