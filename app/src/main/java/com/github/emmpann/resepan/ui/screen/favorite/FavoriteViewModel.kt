package com.github.emmpann.resepan.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.resepan.data.FoodRepository
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: FoodRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Food>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Food>>> get() = _uiState

    fun getAllFavoriteRecipe() {
        viewModelScope.launch {
            repository.getAllFavoriteRecipes()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { favRecipe ->
                    _uiState.value = UiState.Success(favRecipe)
                }
        }
    }
}