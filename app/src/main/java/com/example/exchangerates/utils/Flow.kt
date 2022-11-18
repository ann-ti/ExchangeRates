package com.example.exchangerates.utils


import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Spinner.onQueryClickedItem(): Flow<String> {
    return callbackFlow {
        val textChangeListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                trySendBlocking(p0.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                trySendBlocking(p0.toString())

            }

        }
        this@onQueryClickedItem.onItemSelectedListener = textChangeListener
        awaitClose {
            this@onQueryClickedItem.onItemSelectedListener = null
        }
    }
}