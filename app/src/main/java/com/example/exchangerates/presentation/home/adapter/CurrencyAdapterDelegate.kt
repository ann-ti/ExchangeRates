package com.example.exchangerates.presentation.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.databinding.ItemCurrencyBinding
import com.example.exchangerates.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CurrencyAdapterDelegate(private val itemSelected: ItemSelected) :
    AbsListItemAdapterDelegate<RatesName, RatesName, CurrencyAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: RatesName,
        items: MutableList<RatesName>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_currency), itemSelected)
    }

    override fun onBindViewHolder(
        item: RatesName,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        view: View,
        private val itemSelected: ItemSelected
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCurrencyBinding.bind(view)

        fun bind(ratesName: RatesName) {
            binding.txtValueCurrency.text = ratesName.valueRates.toString()
            binding.txtShortCurrency.text = ratesName.nameRates
            binding.buttonStar.setOnClickListener {
                itemSelected.addToFavorite(ratesName)
            }
            if (ratesName.isFavorite!!) {
                binding.buttonStar.setImageResource(R.drawable.ic_star_fill)
            } else binding.buttonStar.setImageResource(R.drawable.ic_star_outline)
        }
    }

    interface ItemSelected {
        fun addToFavorite(item: RatesName)
    }

}