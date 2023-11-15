package com.github.emmpann.resepan.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.di.Injection
import com.github.emmpann.resepan.ui.ViewModelFactory
import com.github.emmpann.resepan.ui.common.UiState
import com.github.emmpann.resepan.ui.contentFormat
import com.github.emmpann.resepan.ui.theme.ResepanTheme
import com.github.emmpann.resepan.ui.theme.Shapes

@Composable
fun DetailScreen(
    foodId: Int,
    viewModel: DetailFoodViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateBack: () -> Unit,
) {
    val isFavorite by viewModel.isFavorite

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFoodById(foodId)
            }

            is UiState.Success -> {
                val data = uiState.data

                viewModel.getFavoriteRecipe(data.id)

                DetailContent(
                    onCheckedChange = { viewModel.saveFavoriteRecipe(data) },
                    onBackClick = navigateBack,
                    imageUrl = data.imageUrl,
                    name = data.name,
                    isFavorite = isFavorite,
                    time = 20,
                    rating = 4.8f,
                    ingredients = contentFormat(data.ingredients),
                    ways = contentFormat(data.ways)
                )
            }

            else -> {}
        }
    }
}

@Composable
fun DetailContent(
    onCheckedChange: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    imageUrl: String,
    name: String,
    isFavorite: Boolean,
    time: Int,
    rating: Float,
    ingredients: String,
    ways: String,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    )

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() }
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.food_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.large)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Text(
                            text = rating.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_access_time_24),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Text(
                            text = String.format(stringResource(id = R.string.time_format), time),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                FavoriteButton(isFavorite = isFavorite, onCheckedChange = onCheckedChange)
            }
            Column {
                Text(
                    text = stringResource(R.string.ingredients_title),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(ingredients)
                Text(
                    text = stringResource(R.string.how_to_cook_title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(ways)
            }
        }
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = onCheckedChange
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.fav_button),
            modifier = modifier.testTag("favButton")
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ResepanTheme {
        DetailScreen(1, navigateBack = { })
    }
}