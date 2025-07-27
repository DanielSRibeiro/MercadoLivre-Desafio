package com.example.mercadolivre.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.ui.screen.home.components.CardProducts

@Composable
fun HomeScreen(
    uiState: HomeState,
    onNavigateToDetail: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
    ) {
        CardProducts(
            modifier = Modifier,
            product = uiState.lastSeenProducts,
            onNavigateToDetail = onNavigateToDetail
        )
        Spacer(modifier = Modifier.height(16.dp))
        CardProducts(
            modifier = Modifier,
            title = "Favoritos",
            product = uiState.favoritesProducts,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}
