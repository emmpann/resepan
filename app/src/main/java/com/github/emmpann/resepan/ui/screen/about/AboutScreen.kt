package com.github.emmpann.resepan.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.emmpann.resepan.ui.theme.ResepanTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://avatars.githubusercontent.com/u/87558302?v=4"),
            contentDescription = "avatar",
            modifier = Modifier
                .padding(top = 48.dp, bottom = 16.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Efan Fitriyan",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(text = "mhdepan@gmail.com")
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    ResepanTheme {
        AboutScreen()
    }
}