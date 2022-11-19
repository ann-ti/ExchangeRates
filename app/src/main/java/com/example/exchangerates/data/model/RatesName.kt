package com.example.exchangerates.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "currency")
data class RatesName(
    @PrimaryKey(autoGenerate = false)
    val nameRates: String,
    val valueRates: Double,
    var isFavorite: Boolean
): Parcelable