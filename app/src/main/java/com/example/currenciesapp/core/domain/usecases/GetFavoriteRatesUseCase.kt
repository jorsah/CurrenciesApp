package com.example.currenciesapp.core.domain.usecases
import com.example.currenciesapp.core.domain.models.toDomain
import com.example.currenciesapp.core.domain.repository.MainRepository
import javax.inject.Inject

class GetFavoriteRatesUseCase @Inject constructor(
    private val repo: MainRepository
) {

    suspend operator fun invoke() =
        repo.getAllFavorites().map { it.toDomain() }
}