package com.github.emmpann.resepan.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import com.github.emmpann.resepan.ui.components.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.emmpann.resepan.di.Injection
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.ViewModelFactory
import com.github.emmpann.resepan.ui.common.UiState
import com.github.emmpann.resepan.ui.components.FoodItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRecipe(query)
            }

            is UiState.Success -> {
                HomeContent(
                    foodList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::getAllRecipe
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    foodList: List<Food>,
    navigateToDetail: (Int) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier.testTag("searchBar")
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(foodList, key = {it.id}) { data ->
                FoodItem(
                    id = data.id,
                    imageUrl = data.imageUrl,
                    title = data.name,
                    time = data.time,
                    rating = data.rating,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }
        }
    }
}