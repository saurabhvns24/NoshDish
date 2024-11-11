package com.example.dish

import retrofit2.http.GET

interface NoshApiService {
    @GET("nosh-assignment")
    suspend fun getDishes(): List<Dish>
}