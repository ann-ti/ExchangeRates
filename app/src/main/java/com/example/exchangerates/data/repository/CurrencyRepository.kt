package com.example.exchangerates.data.repository

import com.example.exchangerates.data.model.CurrencyData
import com.example.exchangerates.data.network.CurrencyApi
import com.example.exchangerates.utils.Request

interface CurrencyRepository {
    suspend fun getCurrency(base:String): Request<CurrencyData>
}

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi
): CurrencyRepository{

    override suspend fun getCurrency(base: String): Request<CurrencyData> {
        return try {
            val response = currencyApi.getCurrency(base)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Request.Success(result)
            } else {
                Request.Error(response.message())
            }
        } catch(e: Exception) {
            Request.Error(e.message ?: "An error occured")
        }
    }

}