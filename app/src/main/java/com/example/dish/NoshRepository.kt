package com.example.dish

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoshRepository {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NoshApiService::class.java)

    suspend fun getDishes(): List<Dish> {
        return apiService.getDishes()
    }
}