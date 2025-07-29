package com.example.mercadolivre.ui.screen.detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mercadolivre.ui.screen.detail.model.DetailState

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    uiState: DetailState,
    onFavoriteClick: () -> Unit
) {
    uiState.productResults?.attribute?.let {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = uiState.productResults.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.height(16.dp))
                DetailBackGround(
                    snackBarHostState = snackBarHostState,
                    picturesList = uiState.productResults.pictures,
                    uiState.isFavorite,
                    onFavoriteClick = onFavoriteClick
                )
            }
            items(it.size) { index ->
                val attribute = it[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.LightGray),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = attribute.name,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.LightGray)
                            .padding(horizontal = 8.dp)
                    )
                    Text(
                        text = attribute.valueName,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.LightGray)
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}