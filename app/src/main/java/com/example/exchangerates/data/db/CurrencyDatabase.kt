package com.example.exchangerates.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exchangerates.data.model.RatesName

@Database(
    entities = [
        RatesName::class
    ], version = CurrencyDatabase.DB_VERSION
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val DB_VERSION = 1
        const val DB_MAME = "currency_database"
    }
}