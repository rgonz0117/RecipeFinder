package com.example.recipefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MealAdapter : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var meals = listOf<Meal>()

    fun submitList(mealList: List<Meal>) {
        meals = mealList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount() = meals.size

    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageViewMeal)
        private val textView: TextView = view.findViewById(R.id.textViewMealName)

        fun bind(meal: Meal) {
            textView.text = meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(imageView)
        }
    }
}