package com.github.emmpann.resepan.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.resepan.data.FoodRepository
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailFoodViewModel(
    private val repository: FoodRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Food>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Food>> get() = _uiState

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> get() = _isFavorite

    fun getFoodById(foodId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFoodById(foodId))
        }
    }

    fun saveFavoriteRecipe(food: Food) {
        _isFavorite.value = !_isFavorite.value
        if (_isFavorite.value) {
            repository.saveFavoriteRecipes(food)
        } else {
            deleteFavoriteRecipe(food.id)
        }
    }

    fun getFavoriteRecipe(foodId: Int) {
        viewModelScope.launch {
            _isFavorite.value =
                repository.getFavoriteRecipes(foodId).firstOrNull()?.isFavorite ?: false
        }
    }

    private fun deleteFavoriteRecipe(foodId: Int) {
        viewModelScope.launch {
            repository.deleteFavoriteRecipe(foodId)
        }
    }
}