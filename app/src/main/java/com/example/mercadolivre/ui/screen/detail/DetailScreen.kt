package com.example.mercadolivre.ui.screen.detail

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mercadolivre.ui.components.commo.DefaultError
import com.example.mercadolivre.ui.components.commo.LoadingView
import com.example.mercadolivre.ui.screen.detail.components.DetailContent

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    uiState: DetailState,
    onFavoriteClick: () -> Unit
) {
    if(uiState.isLoading) {
        LoadingView()
        return
    }
    if(uiState.isError) {
        DefaultError(retry = null)
        return
    }

    DetailContent(
        modifier = modifier,
        snackBarHostState = snackBarHostState,
        uiState = uiState,
        onFavoriteClick = onFavoriteClick
    )
}
