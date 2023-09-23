package com.example.currenciesapp.core.domain.usecases

import com.example.currenciesapp.core.domain.models.Rate
import com.example.currenciesapp.core.domain.repository.MainRepository
import com.example.remote.model.Currency
import javax.inject.Inject

class AddToFavoritesRatesUseCase @Inject constructor(
    private val repo: MainRepository
) {

    suspend operator fun invoke(rate: Rate, base: Currency) =
        repo.addToFavorites(rate, base)
}
