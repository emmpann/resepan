package com.github.emmpann.resepan.data

import android.content.Context
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.model.Recipe
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FoodRepository(context: Context) {
    private var orderFood = mutableListOf<Food>()
    private val jsonString = context.resources.openRawResource(R.raw.dummy_resep_masakan).bufferedReader().use { it.readText() }
    private val gson = Gson()
    private val recipe: Recipe = gson.fromJson(jsonString, Recipe::class.java)

    init {
        if (orderFood.isEmpty()) {
            orderFood = recipe.data.toMutableList()
        }
    }

    fun getAllRecipe(): Flow<List<Food>> = flowOf(orderFood)

    companion object {
        @Volatile
        private var instance: FoodRepository? = null

        fun getInstance(context: Context): FoodRepository =
            instance ?: synchronized(this) {
                FoodRepository(context).apply {
                    instance = this
                }
            }
    }
}