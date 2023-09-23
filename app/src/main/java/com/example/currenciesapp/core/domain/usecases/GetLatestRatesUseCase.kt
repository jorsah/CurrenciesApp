package com.example.currenciesapp.core.domain.usecases

import com.example.currenciesapp.core.domain.repository.MainRepository
import com.example.remote.model.Currency
import javax.inject.Inject

class GetLatestRatesUseCase @Inject constructor(
    private val repo: MainRepository
) {

    suspend operator fun invoke(base: Currency) =
        repo.getRates(base)
}