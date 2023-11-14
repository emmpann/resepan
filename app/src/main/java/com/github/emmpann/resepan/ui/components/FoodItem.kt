package com.github.emmpann.resepan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.emmpann.resepan.R
import com.github.emmpann.resepan.ui.theme.ResepanTheme
import com.github.emmpann.resepan.ui.theme.Shapes

@Composable
fun FoodItem(
    id: Int,
    imageUrl: String,
    title: String,
    time: String,
    rating: Float,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = modifier) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(Shapes.medium)
            )
            Column(
                modifier = modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_access_time_24),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = rating.toString(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemPreview() {
    ResepanTheme {
        FoodItem(
            id = 0,
            imageUrl = "https://img.kurio.network/77GSRizkh7ZeT0cp7P8j1JG0ziY=/440x440/filters:quality(80):watermark(https://kurio-img.kurioapps.com/21/04/15/df401e7c-7b29-428a-9a16-aad1fafe07a1.png,0,5p,0,22)/https://kurio-img.kurioapps.com/20/10/10/a7e9eaa0-1c22-42b0-a11f-0a5ad1d30126.jpeg",
            title = "Nasi goreng",
            time = "20 menit",
            rating = 2.3f
        )
    }
}
