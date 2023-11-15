package com.github.emmpann.resepan.data

import android.content.Context
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.model.FakeFavoriteRecipe
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.model.Recipe
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FoodRepository(context: Context) {
    private var recipes = mutableListOf<Food>()
    private val jsonString =
        context.resources.openRawResource(R.raw.dummy_resep_masakan).bufferedReader()
            .use { it.readText() }
    private val gson = Gson()
    private val recipe: Recipe = gson.fromJson(jsonString, Recipe::class.java)

    private var favoriteRecipes = mutableListOf<Food>()

    init {
        if (recipes.isEmpty()) {
            recipes = recipe.data.toMutableList()
        }
        if (favoriteRecipes.isEmpty()) {
            favoriteRecipes = FakeFavoriteRecipe.dummyFavoriteRecipe
        }
    }

    fun getFoodById(foodId: Int) = recipes.first { it.id == foodId }

    fun searchFood(query: String): Flow<List<Food>> = flowOf(
        recipes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    )

    fun saveFavoriteRecipes(food: Food) {
        food.isFavorite = true
        favoriteRecipes.add(food)
    }

    fun getFavoriteRecipesById(foodId: Int): Flow<Food?> =
        flowOf(favoriteRecipes.find { it.id == foodId })

    fun getAllFavoriteRecipes(): Flow<List<Food>> = flowOf(favoriteRecipes)
    fun deleteFavoriteRecipeById(foodId: Int) {
        favoriteRecipes.removeIf { it.id == foodId }
    }

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