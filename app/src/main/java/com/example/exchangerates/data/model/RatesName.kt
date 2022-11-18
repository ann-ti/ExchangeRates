package com.example.exchangerates.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class RatesName(
    @PrimaryKey(autoGenerate = false)
    val nameRates: String,
    val valueRates: Double,
    var isFavorite: Boolean
)