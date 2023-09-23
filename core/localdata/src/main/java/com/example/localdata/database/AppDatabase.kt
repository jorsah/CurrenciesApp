package com.example.localdata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localdata.dao.RateDao
import com.example.localdata.model.RateEntity

@Database(
    entities = [
        RateEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao
}
