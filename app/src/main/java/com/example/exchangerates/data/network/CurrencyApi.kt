package com.example.exchangerates.data.network

import com.example.exchangerates.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("exchangerates_data/latest")
    suspend fun getCurrency(
        @Query("base") base: String
    ): Response<CurrencyResponse>
}