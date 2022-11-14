package com.example.exchangerates.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun dateFormat(oldStringDate: Long): String =
    SimpleDateFormat("dd.MM.yyyy", Locale(getCountry())).format(Date(oldStringDate))

private fun getCountry(): String =
    Locale.getDefault().country.lowercase(Locale.ROOT)

enum class LoadState {
    LOADING,
    ERROR,
    SUCCESS,
    EMPTY
}

object RequestUtils {

    fun <T> requestFlow(requestFunc: suspend  () -> T): Flow<Request<T>> {
        return flow<Request<T>>{
            emit(Request.Success(requestFunc()))
        }.onStart {
            emit(Request.Loading())
        }.catch { error ->
            emit(Request.Error(error))
        }
    }
}