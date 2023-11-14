package com.github.emmpann.resepan.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.resepan.data.FoodRepository
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFoodViewModel(
    private val repository: FoodRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Food>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Food>> get() = _uiState

    fun getFoodById(foodId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFoodById(foodId))
        }
    }
}