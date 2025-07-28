package com.example.mercadolivre.ui.components.commo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.mercadolivre.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    onResultClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val expanded = true

    SearchBar(
        modifier = modifier.fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { onSearch(query) },
                expanded = expanded,
                onExpandedChange = {},
                placeholder = { Text(text = stringResource(R.string.label_search)) },
                leadingIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.description_back)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { onSearch(query) }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = stringResource(R.string.description_search)
                        )
                    }
                }
            )
        },
        expanded = expanded,
        onExpandedChange = {}
    ) {
        LazyColumn {
            items(searchResults.size) { index ->
                val result = searchResults[index]
                ListItem(
                    headlineContent = { Text(result) },
                    modifier = Modifier
                        .clickable {
                            onResultClick(result)
                        }
                        .fillMaxWidth(),
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                )
            }
        }
    }
}