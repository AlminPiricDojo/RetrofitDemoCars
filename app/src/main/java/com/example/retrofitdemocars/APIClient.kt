package com.example.retrofitdemocars

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit?{
        retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/AlminPiricDojo/JSON_files/main/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}