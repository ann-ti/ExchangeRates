package com.example.exchangerates.data.repository

import com.example.exchangerates.data.model.CurrencyResponse
import com.example.exchangerates.data.network.CurrencyApi
import com.example.exchangerates.utils.Request
import com.example.exchangerates.utils.RequestUtils.requestFlow
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getCurrency(base: String): Flow<Request<CurrencyResponse>>
}

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {

    override suspend fun getCurrency(base: String): Flow<Request<CurrencyResponse>> {
        return requestFlow {
            val response = currencyApi.getCurrency(base)
            val result = response.body()
            result!!
        }
    }
}