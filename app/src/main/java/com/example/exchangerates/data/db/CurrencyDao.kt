package com.example.exchangerates.data.db

import androidx.room.*
import com.example.exchangerates.data.model.RatesName
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getFavoritesCurrency(): Flow<List<RatesName>>

    @Query("SELECT * FROM currency WHERE nameRates=:currencyId")
    suspend fun getCurrencyId(currencyId: String): RatesName

    @Update
    suspend fun updateCurrency(currency: RatesName)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveCurrencyList(currencyList: List<RatesName?>)

    @Update
    suspend fun updateCurrencyList(currencyList: List<RatesName?>)
}