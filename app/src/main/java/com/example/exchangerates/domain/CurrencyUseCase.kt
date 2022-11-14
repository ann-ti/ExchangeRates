package com.example.exchangerates.domain

import com.example.exchangerates.data.model.CurrencyData
import com.example.exchangerates.data.repository.CurrencyRepository
import com.example.exchangerates.utils.Request
import kotlinx.coroutines.flow.Flow

interface CurrencyUseCase {
    suspend fun getCurrency(base:String): Flow<Request<CurrencyData>>
}

class CurrencyUseCaseImpl(
    private val currencyRepository: CurrencyRepository
) : CurrencyUseCase {
    override suspend fun getCurrency(base: String): Flow<Request<CurrencyData>> {
        val result = currencyRepository.getCurrency(base)
        result.collect {
            if (it is Request.Success) {
                Request.Success(result)
            }
        }
        return result
    }

}