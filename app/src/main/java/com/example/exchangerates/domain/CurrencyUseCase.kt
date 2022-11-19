package com.example.exchangerates.domain

import com.example.exchangerates.data.model.CurrencyResponse
import com.example.exchangerates.data.model.Rates
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.data.repository.CurrencyRepository
import com.example.exchangerates.utils.Request
import com.example.exchangerates.utils.listOfCurrency
import kotlinx.coroutines.flow.Flow

interface CurrencyUseCase {
    suspend fun getCurrency(base: String): Flow<Request<CurrencyResponse>>
    suspend fun getCurrencyId(currencyId: String): RatesName
    suspend fun updateCurrency(currency: RatesName)
    fun getFavoritesCurrency(): Flow<List<RatesName>>
    suspend fun saveCurrencyList(currencyList: List<RatesName?>)
}

class CurrencyUseCaseImpl(
    private val currencyRepository: CurrencyRepository
) : CurrencyUseCase {
    override suspend fun getCurrency(base: String): Flow<Request<CurrencyResponse>> {
        val result = currencyRepository.getCurrency(base)
        result.collect {
            if (it is Request.Success) {
                Request.Success(result)
                val currency = it.data.rates
                val listOfValueCurrency = listOfCurrency.map { nameRates ->
                    getRateForCurrency(nameRates, currency)?.let { it1 ->
                        RatesName(
                            nameRates,
                            it1,
                            getCurrencyId(nameRates).isFavorite
                        )
                    }
                }
                currencyRepository.saveCurrencyList(listOfValueCurrency)
                currencyRepository.updateCurrencyList(listOfValueCurrency)
            }
        }
        return result
    }

    override suspend fun getCurrencyId(currencyId: String): RatesName =
        currencyRepository.getCurrencyId(currencyId)

    override suspend fun updateCurrency(currency: RatesName) {
        currencyRepository.updateCurrency(currency)
    }

    override fun getFavoritesCurrency(): Flow<List<RatesName>> =
        currencyRepository.getFavoritesCurrency()


    override suspend fun saveCurrencyList(currencyList: List<RatesName?>) {
        currencyRepository.saveCurrencyList(currencyList)
    }

    private fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
        "CAD" -> rates.cAD
        "HKD" -> rates.hKD
        "ISK" -> rates.iSK
        "EUR" -> rates.eUR
        "PHP" -> rates.pHP
        "DKK" -> rates.dKK
        "HUF" -> rates.hUF
        "CZK" -> rates.cZK
        "AUD" -> rates.aUD
        "RON" -> rates.rON
        "SEK" -> rates.sEK
        "IDR" -> rates.iDR
        "INR" -> rates.iNR
        "BRL" -> rates.bRL
        "RUB" -> rates.rUB
        "HRK" -> rates.hRK
        "JPY" -> rates.jPY
        "THB" -> rates.tHB
        "CHF" -> rates.cHF
        "SGD" -> rates.sGD
        "PLN" -> rates.pLN
        "BGN" -> rates.bGN
        "CNY" -> rates.cNY
        "NOK" -> rates.nOK
        "NZD" -> rates.nZD
        "ZAR" -> rates.zAR
        "USD" -> rates.uSD
        "MXN" -> rates.mXN
        "ILS" -> rates.iLS
        "GBP" -> rates.gBP
        "KRW" -> rates.kRW
        "MYR" -> rates.mYR
        else -> null
    }

}