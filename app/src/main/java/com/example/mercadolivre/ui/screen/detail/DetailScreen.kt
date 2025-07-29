package com.example.mercadolivre.ui.screen.detail

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mercadolivre.ui.screen.detail.components.DetailContent
import com.example.mercadolivre.ui.screen.detail.model.DetailState

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    uiState: DetailState,
    onFavoriteClick: () -> Unit
) {
    DetailContent(
        modifier = modifier,
        snackBarHostState = snackBarHostState,
        uiState = uiState,
        onFavoriteClick = onFavoriteClick
    )
}
