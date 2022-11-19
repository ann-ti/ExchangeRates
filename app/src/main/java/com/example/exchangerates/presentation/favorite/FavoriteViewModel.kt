package com.example.exchangerates.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.domain.CurrencyUseCase
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteViewModel(
    private val currencyUseCase: CurrencyUseCase
) : ViewModel() {

    private val currencyMutableState = MutableStateFlow<List<RatesName>>(emptyList())
    val currency: StateFlow<List<RatesName>> = currencyMutableState

    private var errorData = LiveEvent<String>()
    private var errorViewData = LiveEvent<Boolean>()

    val error: LiveEvent<String>
        get() = errorData
    val errorView: LiveEvent<Boolean>
        get() = errorViewData

    fun getFavoritesCurrency() {
        currencyUseCase.getFavoritesCurrency()
            .onEach {
                val listFavCurrency = it.filter { ratesName ->
                    ratesName.isFavorite
                }
                currencyMutableState.value = listFavCurrency
            }
            .launchIn(viewModelScope)
    }

}