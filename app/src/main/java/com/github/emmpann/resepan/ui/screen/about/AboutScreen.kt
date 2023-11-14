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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.emmpann.resepan.R
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
            painter = rememberAsyncImagePainter(model = stringResource(R.string.avatar_url)),
            contentDescription = stringResource(R.string.avatar_description),
            modifier = Modifier
                .padding(top = 48.dp, bottom = 16.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(R.string.user_name),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(text = stringResource(R.string.user_email))
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    ResepanTheme {
        AboutScreen()
    }
}