package com.github.emmpann.resepan.ui.theme.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.emmpann.resepan.model.OrderFood

@Composable
fun HomeContent(
    orderFood: List<OrderFood>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
//        items(orderFood) { data ->
//
//        }
    }
}