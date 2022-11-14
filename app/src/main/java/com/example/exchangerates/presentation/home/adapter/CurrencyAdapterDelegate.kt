package com.example.exchangerates.presentation.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.model.CurrencyData
import com.example.exchangerates.data.model.Rates
import com.example.exchangerates.databinding.ItemCurrencyBinding
import com.example.exchangerates.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CurrencyAdapterDelegate() :
    AbsListItemAdapterDelegate<CurrencyData, CurrencyData, CurrencyAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: CurrencyData,
        items: MutableList<CurrencyData>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_currency))
    }

    override fun onBindViewHolder(
        item: CurrencyData,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCurrencyBinding.bind(view)

        fun bind(currency: CurrencyData) {

        }
    }

}