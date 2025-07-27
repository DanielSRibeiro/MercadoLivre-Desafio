package com.example.mercadolivre.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.ui.screen.detail.components.DetailBackGround

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    uiState: DetailState,
    onFavoriteClick: () -> Unit
) {
    val picturesList = listOf(
        "https://http2.mlstatic.com/D_915891-MLU72341214315_102023-O.jpg",
        "https://http2.mlstatic.com/D_789536-MLU72277074990_102023-O.jpg",
        "https://http2.mlstatic.com/D_779280-MLU71274517719_082023-O.jpg",
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = "Café Torrado E Moído Extraforte 250g 3 Corações"
        )
        Spacer(Modifier.padding(12.dp))
        DetailBackGround(
            snackBarHostState = snackBarHostState,
            picturesList = picturesList,
            uiState.isFavorite,
            onFavoriteClick = onFavoriteClick
        )
    }
}
