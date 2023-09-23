package com.example.currenciesapp.core.domain.usecases

import com.example.currenciesapp.core.domain.models.FavoriteRate
import com.example.currenciesapp.core.domain.repository.MainRepository
import com.example.remote.model.Currency
import javax.inject.Inject

class DeleteFavoriteRateUseCase @Inject constructor(
    private val repo: MainRepository
) {

    suspend operator fun invoke(toCurrency: Currency, base: Currency) =
        repo.deleteFromFavorites(base, toCurrency)

    suspend operator fun invoke(favoriteRate: FavoriteRate) =
        repo.deleteFromFavorites(favoriteRate.fromCurrency, favoriteRate.toCurrency)
}
