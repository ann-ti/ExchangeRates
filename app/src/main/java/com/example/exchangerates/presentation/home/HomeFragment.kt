package com.example.exchangerates.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.databinding.FragmentHomeBinding
import com.example.exchangerates.presentation.home.adapter.CurrencyAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()
    private val adapterCurrency: CurrencyAdapter by lazy { CurrencyAdapter() }

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

    }

    private fun setRecyclerViewCurrency() {
        with(binding.listCurrency) {
            adapter = adapterCurrency
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }


}