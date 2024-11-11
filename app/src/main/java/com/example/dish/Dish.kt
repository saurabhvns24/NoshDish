package com.example.dish

data class Dish(
    val dishId: String,
    val dishName: String,
    val imageUrl: String,
    val isPublished: Boolean,
    val rating: Double
)
