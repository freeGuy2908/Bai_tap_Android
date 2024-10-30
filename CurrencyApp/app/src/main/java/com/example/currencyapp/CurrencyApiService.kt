package com.example.currencyapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("symbols")
    fun getCurrencySymbols(@Query("access_key") apiKey: String): Call<CurrencySymbolsResponse>

    @GET("latest")
    fun getExchangeRates(
        @Query("access_key") apiKey: String,
        @Query("base") baseCurrency: String
    ): Call<ExchangeRatesResponse>
}
