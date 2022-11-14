package com.example.exchangerates.di

import com.example.exchangerates.data.network.CurrencyApi
import com.example.exchangerates.data.network.RetrofitFactory
import com.example.exchangerates.data.repository.CurrencyRepository
import com.example.exchangerates.data.repository.CurrencyRepositoryImpl
import com.example.exchangerates.domain.CurrencyUseCase
import com.example.exchangerates.domain.CurrencyUseCaseImpl
import com.example.exchangerates.presentation.home.HomeViewModel
import com.example.exchangerates.utils.MoshiInstantAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.exchangeratesapi.io/"

val appModule = module {

    single {
        val okHttp = OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    single {
        get<RetrofitFactory>().makeService<CurrencyApi>(BASE_URL)
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(MoshiInstantAdapter())
            .build()
    }

    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            currencyApi = get()
        )
    }

    single<CurrencyUseCase> {
        CurrencyUseCaseImpl(
            currencyRepository = get()
        )
    }

    viewModel {
        HomeViewModel(
            currencyUseCase = get()
        )
    }

}