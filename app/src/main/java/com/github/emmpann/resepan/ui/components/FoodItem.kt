package com.github.emmpann.resepan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.emmpann.resepan.ui.theme.ResepanTheme

@Composable
fun FoodItem(
    imageUrl: String,
    title: String,
    desc: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = desc,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemPreview() {
    ResepanTheme {
        FoodItem(
            imageUrl = "https://img.kurio.network/77GSRizkh7ZeT0cp7P8j1JG0ziY=/440x440/filters:quality(80):watermark(https://kurio-img.kurioapps.com/21/04/15/df401e7c-7b29-428a-9a16-aad1fafe07a1.png,0,5p,0,22)/https://kurio-img.kurioapps.com/20/10/10/a7e9eaa0-1c22-42b0-a11f-0a5ad1d30126.jpeg",
            title = "Nasi goreng",
            desc = "Nasi goreng adalah hidangan Indonesia yang terdiri dari nasi yang digoreng bersama dengan bumbu-bumbu seperti bawang, cabai, kecap, dan tambahan seperti telur, daging, atau sayuran. Hidangan ini memiliki rasa gurih dan sedikit pedas, dan sering disajikan dengan acar, kerupuk, dan irisan mentimun sebagai pelengkap. Nasi goreng merupakan salah satu makanan yang populer dan dapat ditemukan di banyak warung makan dan restoran di Indonesia."
        )
    }
}