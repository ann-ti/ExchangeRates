package com.example.exchangerates.presentation.favorite

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.databinding.FragmentFavoriteBinding
import com.example.exchangerates.presentation.favorite.adapter.FavoriteAdapter
import com.example.exchangerates.presentation.favorite.adapter.FavoriteAdapterDelegate
import com.example.exchangerates.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment: Fragment(R.layout.fragment_favorite), FavoriteAdapterDelegate.ItemSelected {

    private lateinit var binding: FragmentFavoriteBinding
    private val adapterFavorite: FavoriteAdapter by lazy { FavoriteAdapter(this) }
    private val viewModel by viewModel<FavoriteViewModel>()
    private val viewModelHome by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewFavorite()
        getFavCurrency()
    }

    private fun setRecyclerViewFavorite() {
        with(binding.listCurrencyFav) {
            adapter = adapterFavorite
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }

    private fun getFavCurrency() {
        viewModel.getFavoritesCurrency()
        lifecycleScope.launchWhenCreated {
            viewModel.currency.collect {
                adapterFavorite.items = it
            }
        }
    }

    override fun removeFromFavorite(item: RatesName) {
        AlertDialog.Builder(context)
            .setMessage("Вы точно хотите удалить из избранного?")
            .setPositiveButton("да, точно") { dialog, which ->
                viewModelHome.removeCurrency(item.nameRates)
            }
            .setNegativeButton("нет", null)
            .create()
            .show()
    }


}