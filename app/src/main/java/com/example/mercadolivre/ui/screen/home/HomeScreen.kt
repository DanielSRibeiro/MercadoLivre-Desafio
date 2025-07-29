package com.example.mercadolivre.ui.screen.home

import androidx.compose.runtime.Composable
import com.example.mercadolivre.ui.components.commo.DefaultError
import com.example.mercadolivre.ui.components.commo.ErrorNetworkScreen
import com.example.mercadolivre.ui.components.commo.LoadingView
import com.example.mercadolivre.ui.screen.detail.model.ProductResultsDetail
import com.example.mercadolivre.ui.screen.home.components.HomeContent

@Composable
fun HomeScreen(
    uiState: HomeState,
    onNavigateToDetail: (ProductResultsDetail) -> Unit,
    retry: () -> Unit,
    onNavigateToSearch: () -> Unit,
) {
    if (uiState.isLoading) {
        LoadingView()
        return
    }
    if (uiState.isNetworkError) {
        ErrorNetworkScreen(retry = retry)
        return
    }
    if (uiState.isError) {
        DefaultError(retry = retry)
        return
    }

    HomeContent(
        uiState = uiState,
        onNavigateToDetail = onNavigateToDetail,
        onNavigateToSearch = onNavigateToSearch
    )
}
