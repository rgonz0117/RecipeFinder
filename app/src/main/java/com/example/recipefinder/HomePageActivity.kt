package com.example.recipefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageActivity : AppCompatActivity() {

    private lateinit var randomFoodImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        randomFoodImageView = findViewById(R.id.imageViewRandomFood)
        val button: Button = findViewById(R.id.buttonFindRecipe)

        // Initial load of random meal
        loadRandomMealImage()

        // Change image on click
        randomFoodImageView.setOnClickListener {
            loadRandomMealImage()
        }

        // Button to navigate to RecipeListActivity
        button.setOnClickListener {
            startActivity(Intent(this, RecipeListActivity::class.java))
        }
    }

    private fun loadRandomMealImage() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val randomMealResponse = RetrofitClient.apiService.getRandomMeal()
                val randomMeal = randomMealResponse.meals.firstOrNull()

                randomMeal?.let {
                    withContext(Dispatchers.Main) {
                        Picasso.get().load(it.strMealThumb).into(randomFoodImageView)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@HomePageActivity, "Failed to load random meal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}