package com.example.exchangerates.data.network

import okhttp3.Interceptor
import okhttp3.Response


class NetInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("apikey", "X0GIuqUQZnDNG1ff1Xr1RZnJCTGxSfXB")
            .build()
        return chain.proceed(request)
    }
}