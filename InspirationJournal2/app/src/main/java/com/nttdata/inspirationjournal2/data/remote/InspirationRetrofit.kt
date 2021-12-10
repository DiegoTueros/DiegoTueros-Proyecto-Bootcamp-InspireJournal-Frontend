package com.nttdata.inspirationjournal2.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InspirationRetrofit {
    private const val URL_BASE = "http://192.168.18.6:4000"

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(this)
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun createService(): InspirationService{
        return retrofit.create(InspirationService::class.java)
    }
}