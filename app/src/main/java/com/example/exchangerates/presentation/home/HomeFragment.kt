package com.example.exchangerates.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.data.model.RatesName
import com.example.exchangerates.databinding.FragmentHomeBinding
import com.example.exchangerates.presentation.home.adapter.CurrencyAdapter
import com.example.exchangerates.presentation.home.adapter.CurrencyAdapterDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), CurrencyAdapterDelegate.ItemSelected {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()
    private val adapterCurrency: CurrencyAdapter by lazy { CurrencyAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewCurrency()
        getCurrency()
        sort()
    }

    private fun setRecyclerViewCurrency() {
        with(binding.listCurrency) {
            adapter = adapterCurrency
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }

    private fun getCurrency() {

        binding.buttonSearch.setOnClickListener {
            viewModel.getCurrency(binding.spinner.selectedItem.toString())
            viewModel.getListCurrencyDb()
        }
        lifecycleScope.launchWhenCreated {
            viewModel.ratesState.collect {
                adapterCurrency.items = it
            }
        }
    }

    override fun addToFavorite(item: RatesName) {
        viewModel.saveOrRemoveCurrency(item)
    }

    private fun sort() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuSortValue -> {
                    it.isChecked = true
                    viewModel.sortValue()
                    lifecycleScope.launchWhenCreated {
                        viewModel.ratesState.collect {
                            adapterCurrency.items = it
                        }
                    }
                }
                R.id.menuSortAlphabet -> {
                    it.isChecked = true
                    viewModel.sortAlphabet()
                    lifecycleScope.launchWhenCreated {
                        viewModel.ratesState.collect {
                            adapterCurrency.items = it
                        }
                    }
                }
            }
            false
        }
    }
}
