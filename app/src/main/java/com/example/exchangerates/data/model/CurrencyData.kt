package com.example.exchangerates.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyData(
    @Json(name = "base")
    val base: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "rates")
    val rates: Rates,
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "timestamp")
    val timestamp: Long
)

@JsonClass(generateAdapter = true)
data class Rates(
    @Json(name = "AUD")
    val aUD: Double,
    @Json(name = "BGN")
    val bGN: Double,
    @Json(name = "EUR")
    val eUR: Double,
    @Json(name = "BRL")
    val bRL: Double,
    @Json(name = "CAD")
    val cAD: Double,
    @Json(name = "CHF")
    val cHF: Double,
    @Json(name = "CNY")
    val cNY: Double,
    @Json(name = "CZK")
    val cZK: Double,
    @Json(name = "DKK")
    val dKK: Double,
    @Json(name = "GBP")
    val gBP: Double,
    @Json(name = "HKD")
    val hKD: Double,
    @Json(name = "HRK")
    val hRK: Double,
    @Json(name = "HUF")
    val hUF: Double,
    @Json(name = "IDR")
    val iDR: Double,
    @Json(name = "ILS")
    val iLS: Double,
    @Json(name = "INR")
    val iNR: Double,
    @Json(name = "ISK")
    val iSK: Double,
    @Json(name = "JPY")
    val jPY: Double,
    @Json(name = "KRW")
    val kRW: Double,
    @Json(name = "MXN")
    val mXN: Double,
    @Json(name = "MYR")
    val mYR: Double,
    @Json(name = "NOK")
    val nOK: Double,
    @Json(name = "NZD")
    val nZD: Double,
    @Json(name = "PHP")
    val pHP: Double,
    @Json(name = "PLN")
    val pLN: Double,
    @Json(name = "RON")
    val rON: Double,
    @Json(name = "RUB")
    val rUB: Double,
    @Json(name = "SEK")
    val sEK: Double,
    @Json(name = "SGD")
    val sGD: Double,
    @Json(name = "THB")
    val tHB: Double,
    @Json(name = "TRY")
    val tRY: Double,
    @Json(name = "USD")
    val uSD: Double,
    @Json(name = "ZAR")
    val zAR: Double
)