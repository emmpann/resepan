package com.github.emmpann.resepan.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.emmpann.resepan.data.FoodRepository
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.model.Recipe
import com.github.emmpann.resepan.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FoodRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Food>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Food>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllRecipe() {
        viewModelScope.launch {
            repository.getAllRecipe()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderFood ->
                    _uiState.value = UiState.Success(orderFood)
                }
        }
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchFood(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderFood ->
                    _uiState.value = UiState.Success(orderFood)
                }
        }
    }
}