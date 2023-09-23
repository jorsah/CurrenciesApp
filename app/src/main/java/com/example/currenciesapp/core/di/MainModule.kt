package com.example.currenciesapp.core.di

import com.example.currenciesapp.core.data.repository.MainRepositoryImpl
import com.example.currenciesapp.core.domain.repository.MainRepository
import com.example.localdata.dao.RateDao
import com.example.remote.service.CurrenciesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrencyModule {

    @Provides
    @Singleton
    fun provideCurrenciesService(
        retrofit: Retrofit
    ): CurrenciesService = retrofit.create(CurrenciesService::class.java)

    @Provides
    @Singleton
    fun provideMainRepository(
        service: CurrenciesService,
        dao: RateDao
    ): MainRepository = MainRepositoryImpl(service,dao)
}
