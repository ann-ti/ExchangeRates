package com.example.exchangerates.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.data.model.Rates
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.domain.CurrencyUseCase
import com.example.exchangerates.utils.LoadState
import com.example.exchangerates.utils.Request
import com.example.exchangerates.utils.listOfCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(
    private val currencyUseCase: CurrencyUseCase
): ViewModel() {

    private val loadStateFlow = MutableStateFlow<LoadState>(LoadState.EMPTY)
    val loadState: StateFlow<LoadState> = loadStateFlow

    private val ratesStateFlow = MutableStateFlow<List<RatesName>>(emptyList())
    val ratesState: StateFlow<List<RatesName>> = ratesStateFlow

    fun getCurrency(base: String){
        viewModelScope.launch {
            currencyUseCase.getCurrency(base).collect{ requestState ->
                when(requestState){
                    is Request.Loading -> {
                        loadStateFlow.value = LoadState.LOADING
                    }
                    is Request.Success -> {
                        loadStateFlow.value = LoadState.SUCCESS
                        val currency= requestState.data.rates
                        val listOfValueCurrency = listOfCurrency.map {
                            getRateForCurrency(it, currency)?.let { it1 -> RatesName(it, it1) }
                        }
                        ratesStateFlow.value = listOfValueCurrency as List<RatesName>
                    }
                    is Request.Error -> {
                        loadStateFlow.value = LoadState.ERROR
                    }
                }
            }
        }
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