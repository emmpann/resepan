package com.github.emmpann.resepan.ui.theme.screen.detail

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFoodById(foodId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    imageUrl = data.imageUrl,
                    name = data.name,
                    time = "20 menit",
                    rating = "4.5",
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
    imageUrl: String,
    name: String,
    time: String,
    rating: String,
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
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Image(
                painter = painter,//painterResource(id = R.drawable.nasgor_image),
                contentScale = ContentScale.Crop,
                contentDescription = "food_image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.large)
            )
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = rating,
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
                        text = time,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Column {
                Text(
                    text = "Bahan Utama",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(ingredients)
                Text(
                    text = "Cara Memasak",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(ways)
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ResepanTheme {
        DetailContent(
            imageUrl = "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2023/07/13073811/Praktis-dengan-Bahan-Sederhana-Ini-Resep-Nasi-Goreng-Special-1.jpg.webp",
            name = "Pisang Goreng",
            time = "25 menit",
            rating = "5.0",
            ingredients = "- Pisang\n- Tepung serbaguna\n- Minyak goreng",
            ways = "- Siapkan tepung basah\n- Potong pisang sesuai selera\n- Baluri pisang dengan tepung basah\n- Goreng hingga golden brown"
        )
    }
}