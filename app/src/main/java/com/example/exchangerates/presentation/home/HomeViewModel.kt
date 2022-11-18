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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val currencyUseCase: CurrencyUseCase
) : ViewModel() {

    private val loadStateFlow = MutableStateFlow<LoadState>(LoadState.EMPTY)
    val loadState: StateFlow<LoadState> = loadStateFlow

    private val ratesStateFlow = MutableStateFlow<List<RatesName>>(emptyList())
    val ratesState: StateFlow<List<RatesName>> = ratesStateFlow

    fun getCurrency(base: String) {
        viewModelScope.launch {
            currencyUseCase.getCurrency(base).collect { requestState ->
                when (requestState) {
                    is Request.Loading -> {
                        loadStateFlow.value = LoadState.LOADING
                    }
                    is Request.Success -> {
                        loadStateFlow.value = LoadState.SUCCESS
                    }
                    is Request.Error -> {
                        loadStateFlow.value = LoadState.ERROR
                    }
                }
            }
        }
    }

    fun getListCurrencyDb(){
        currencyUseCase.getFavoritesCurrency()
            .onEach { ratesStateFlow.value = it }
            .launchIn(viewModelScope)
    }

    fun saveOrRemoveCurrency(currency: RatesName) {
        viewModelScope.launch {
            try {
                if (currency.isFavorite) {
                    removeCurrency(currency.nameRates)
                } else saveCurrency(currency.nameRates)
                currencyUseCase.updateCurrency(currency)
            } catch (e: Throwable) {

            }
        }
    }

    fun saveCurrency(id: String) {
        viewModelScope.launch {
            try {
                val favoriteCurrency = currencyUseCase.getCurrencyId(id).apply {
                    this.isFavorite = true
                }
                currencyUseCase.updateCurrency(favoriteCurrency)
            } catch (e: Throwable) {

            }
        }
    }

    fun removeCurrency(id: String) {
        viewModelScope.launch {
            try {
                val favoriteCurrency = currencyUseCase.getCurrencyId(id).apply {
                    this.isFavorite = false
                }
                currencyUseCase.updateCurrency(favoriteCurrency)
            } catch (e: Throwable) {

            }
        }
    }

}