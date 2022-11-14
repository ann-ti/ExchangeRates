package com.example.exchangerates.presentation.home

import androidx.lifecycle.ViewModel
import com.example.exchangerates.domain.CurrencyUseCase

class HomeViewModel(
    private val currencyUseCase: CurrencyUseCase
): ViewModel() {
}