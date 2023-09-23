package com.example.localdata.dao

import androidx.room.*
import com.example.localdata.model.CurrencyEntity
import com.example.localdata.model.RateEntity

@Dao
interface RateDao {

    @Query("SELECT * FROM rates")
    suspend fun getRates(): List<RateEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRate(rate: RateEntity)

    @Query("DELETE FROM rates WHERE `from` = :from AND `to` = :to")
    suspend fun deleteRate(from:CurrencyEntity, to:CurrencyEntity)
}
