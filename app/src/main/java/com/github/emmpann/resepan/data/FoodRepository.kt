package com.github.emmpann.resepan.data

import android.content.Context
import android.util.Log
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.model.Recipe
import com.github.emmpann.resepan.ui.common.UiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FoodRepository(context: Context) {
    private var recipes = mutableListOf<Food>()
    private val jsonString =
        context.resources.openRawResource(R.raw.dummy_resep_masakan).bufferedReader()
            .use { it.readText() }
    private val gson = Gson()
    private val recipe: Recipe = gson.fromJson(jsonString, Recipe::class.java)

    init {
        if (recipes.isEmpty()) {
            recipes = recipe.data.toMutableList()
        }
    }

    fun getAllRecipe(): Flow<List<Food>> = flowOf(recipes)

    fun getFoodById(foodId: Int) = recipes.first { it.id == foodId }

    fun searchFood(query: String): Flow<List<Food>> = flowOf(
        recipes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    )

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