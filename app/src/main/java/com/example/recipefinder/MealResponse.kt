package com.example.recipefinder

data class MealResponse(val meals: List<Meal>)
data class Meal(val idMeal: String, val strMeal: String, val strMealThumb: String)

data class MealDetailResponse(val meals: List<MealDetail>)
data class MealDetail(
    val strMeal: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strYoutube: String
)