package com.example.exchangerates.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exchangerates.data.model.CurrencyResponse
import com.example.exchangerates.data.model.Rates
import com.example.exchangerates.data.model.RatesName
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CurrencyAdapter :
    AsyncListDifferDelegationAdapter<RatesName>(ApplicationDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(CurrencyAdapterDelegate())
    }

    class ApplicationDiffUtilCallback : DiffUtil.ItemCallback<RatesName>() {
        override fun areItemsTheSame(
            oldItem: RatesName,
            newItem: RatesName
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RatesName,
            newItem: RatesName
        ): Boolean {
            return oldItem == newItem
        }
    }
}