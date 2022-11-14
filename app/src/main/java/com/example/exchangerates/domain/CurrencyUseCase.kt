package com.example.exchangerates.domain

import com.example.exchangerates.data.model.CurrencyData
import com.example.exchangerates.data.repository.CurrencyRepository
import com.example.exchangerates.utils.Request

interface CurrencyUseCase {
    suspend fun getCurrency(base:String): Request<CurrencyData>
}

class CurrencyUseCaseImpl(
    private val currencyRepository: CurrencyRepository
) : CurrencyUseCase {
    override suspend fun getCurrency(base: String): Request<CurrencyData> {
        TODO("Not yet implemented")
    }

}