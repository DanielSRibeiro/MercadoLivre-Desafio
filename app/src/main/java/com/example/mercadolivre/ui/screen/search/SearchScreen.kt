package com.example.mercadolivre.ui.screen.search

import androidx.compose.runtime.Composable
import com.example.mercadolivre.ui.components.commo.SearchBarComponent

@Composable
fun SearchScreen(
    uiState: SearchState,
    onQueryChange: (String) -> Unit,
    popBackStack: () -> Unit,
    onSearch: (query: String) -> Unit,
) {
    SearchBarComponent(
        query = uiState.query,
        onQueryChange = { onQueryChange(it) },
        onSearch = { onSearch(it) },
        onBackClick = { popBackStack() },
        searchResults = uiState.researchHistory,
        onResultClick = { onSearch(it) }
    )
}