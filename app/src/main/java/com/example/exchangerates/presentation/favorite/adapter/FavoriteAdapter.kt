package com.example.exchangerates.presentation.favorite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.presentation.home.adapter.CurrencyAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FavoriteAdapter(itemSelected: FavoriteAdapterDelegate.ItemSelected) :
    AsyncListDifferDelegationAdapter<RatesName>(ApplicationDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(FavoriteAdapterDelegate(itemSelected))
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