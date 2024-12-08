package com.example.recipefinder

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("filter.php?c=chicken")
    suspend fun getMeals(): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") mealId: String): MealDetailResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse
}