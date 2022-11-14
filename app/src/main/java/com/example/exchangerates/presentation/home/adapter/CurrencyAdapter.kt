package com.example.exchangerates.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exchangerates.data.model.CurrencyData
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CurrencyAdapter() :
    AsyncListDifferDelegationAdapter<CurrencyData>(ApplicationDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(CurrencyAdapterDelegate())
    }

    class ApplicationDiffUtilCallback : DiffUtil.ItemCallback<CurrencyData>() {
        override fun areItemsTheSame(
            oldItem: CurrencyData,
            newItem: CurrencyData
        ): Boolean {
            return oldItem.base == newItem.base
        }

        override fun areContentsTheSame(
            oldItem: CurrencyData,
            newItem: CurrencyData
        ): Boolean {
            return oldItem == newItem
        }
    }
}