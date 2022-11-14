package com.example.exchangerates.data.network

import com.example.exchangerates.data.model.CurrencyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/latest")
    suspend fun getCurrency(
        @Query("base") base: String
    ): Response<CurrencyData>
}