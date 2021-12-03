package com.example.retrofitdemocars

data class CarsItem(
    val make: String,
    val model: String,
    val owners: List<String>,
    val year: String
)