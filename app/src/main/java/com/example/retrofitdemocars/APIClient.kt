package com.example.retrofitdemocars

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit?{

        // New Code
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/AlminPiricDojo/JSON_files/main/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // New Code
            .build()
        return retrofit
    }
}