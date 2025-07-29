package com.example.mercadolivre.ui.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.R
import com.example.mercadolivre.ui.components.commo.NoFavoritesScreen
import com.example.mercadolivre.ui.components.commo.NoLastSeenProduct
import com.example.mercadolivre.ui.screen.detail.model.ProductResultsDetail
import com.example.mercadolivre.ui.screen.home.HomeState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeState,
    onNavigateToDetail: (ProductResultsDetail) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.label_recently_viewed),
            style = MaterialTheme.typography.titleMedium
        )
        if (uiState.lastSeenProductData.isEmpty()) {
            NoLastSeenProduct(action = onNavigateToSearch)
        } else {
            CardProducts(
                productData = uiState.lastSeenProductData,
                onNavigateToDetail = onNavigateToDetail
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.label_favorites),
            style = MaterialTheme.typography.titleMedium
        )
        if (uiState.favoritesProductData.isEmpty()) {
            NoFavoritesScreen()
        } else {
            CardProducts(
                productData = uiState.favoritesProductData,
                onNavigateToDetail = onNavigateToDetail,
                isFavorite = true
            )
        }
    }
}