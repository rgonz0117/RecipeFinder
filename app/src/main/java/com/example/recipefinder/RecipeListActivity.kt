package com.example.recipefinder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val mealAdapter = MealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recyclerView = findViewById(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mealAdapter

        // Fetch and display meals
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getMeals()
                withContext(Dispatchers.Main) {
                    mealAdapter.submitList(response.meals)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecipeListActivity, "Failed to load recipes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}