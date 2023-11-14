package com.github.emmpann.resepan.ui.screen.favorite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.emmpann.resepan.di.Injection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.ViewModelFactory
import com.github.emmpann.resepan.ui.common.UiState
import com.github.emmpann.resepan.ui.components.FoodItem
import com.github.emmpann.resepan.ui.theme.ResepanTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFavoriteRecipe()
            }

            is UiState.Success -> {
                FavoriteContent(
                    modifier = modifier,
                    favRecipe = uiState.data,
                    navigateToDetail = navigateToDetail,
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    favRecipe: List<Food>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            Log.d("fav recipe", favRecipe.size.toString())
            if (favRecipe.isNotEmpty()) {
                items(favRecipe) { data ->
                    FoodItem(
                        id = data.id,
                        imageUrl = data.imageUrl,
                        title = data.name,
                        time = "20 minute",
                        rating = 5f,
                        modifier = Modifier.clickable {
                            navigateToDetail(data.id)
                        }
                    )
                }
            } else {
                item {
                    Text(text = stringResource(R.string.data_is_not_found))
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    ResepanTheme {
        FavoriteScreen(navigateToDetail = { })
    }
}