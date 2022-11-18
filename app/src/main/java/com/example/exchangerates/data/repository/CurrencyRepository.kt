package com.example.exchangerates.data.repository

import com.example.exchangerates.data.db.Database
import com.example.exchangerates.data.model.CurrencyResponse
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.data.network.CurrencyApi
import com.example.exchangerates.utils.Request
import com.example.exchangerates.utils.RequestUtils.requestFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface CurrencyRepository {
    suspend fun getCurrency(base: String): Flow<Request<CurrencyResponse>>
    suspend fun getCurrencyId(currencyId: String): RatesName
    suspend fun updateCurrency(currency: RatesName)
    fun getFavoritesCurrency(): Flow<List<RatesName>>
    suspend fun saveCurrencyList(currencyList: List<RatesName?>)
    suspend fun updateCurrencyList(currencyList: List<RatesName?>)
}

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {

    private val currencyDao = Database.instance.currencyDao()

    override suspend fun getCurrency(base: String): Flow<Request<CurrencyResponse>> {
        return requestFlow {
            val response = currencyApi.getCurrency(base)
            val result = response.body()
            result!!
        }
    }

    override suspend fun getCurrencyId(currencyId: String): RatesName =
        withContext(Dispatchers.IO) {
            currencyDao.getCurrencyId(currencyId)
        }

    override suspend fun updateCurrency(currency: RatesName) {
        withContext(Dispatchers.IO) {
            currencyDao.updateCurrency(currency)
        }
    }

    override fun getFavoritesCurrency(): Flow<List<RatesName>> =
        currencyDao.getFavoritesCurrency()

    override suspend fun saveCurrencyList(currencyList: List<RatesName?>) {
        currencyDao.saveCurrencyList(currencyList)
    }

    override suspend fun updateCurrencyList(currencyList: List<RatesName?>) {
        currencyDao.updateCurrencyList(currencyList)
    }

}