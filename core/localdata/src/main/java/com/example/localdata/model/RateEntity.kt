package com.example.localdata.model

import androidx.room.*

@Entity(tableName = "rates")
data class RateEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "from") val from: CurrencyEntity,
    @ColumnInfo(name = "to") val to: CurrencyEntity,
    @ColumnInfo(name = "value") val value: Double
)
