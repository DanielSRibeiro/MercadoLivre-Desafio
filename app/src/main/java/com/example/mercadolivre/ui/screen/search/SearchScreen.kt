package com.example.mercadolivre.ui.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mercadolivre.ui.components.commo.SearchBarComponent

@Composable
fun SearchScreen(
    uiState: SearchState,
    onQueryChange: (String) -> Unit,
    popBackStack: () -> Unit,
    onSearch: (query: String) -> Unit
) {
    SearchBarComponent(
        query = uiState.query,
        onQueryChange = { onQueryChange(it) },
        onSearch = { onSearch(it) },
        onBackClick = { popBackStack() },
        searchResults = listOf("Celular", "Notebook", "Fone", "TV"),
        onResultClick = { onSearch(it) }
    )
}