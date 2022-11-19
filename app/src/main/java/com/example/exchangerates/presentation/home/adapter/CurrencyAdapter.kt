package com.example.exchangerates.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exchangerates.data.model.RatesName
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CurrencyAdapter(itemSelected: CurrencyAdapterDelegate.ItemSelected) :
    AsyncListDifferDelegationAdapter<RatesName>(ApplicationDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(CurrencyAdapterDelegate(itemSelected))
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