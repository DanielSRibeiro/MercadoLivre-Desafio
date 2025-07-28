package com.example.mercadolivre.ui.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.R
import com.example.mercadolivre.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun FavoriteTag(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    snackBarHostState: SnackbarHostState,
    onFavoriteClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val message = if (isFavorite)
        stringResource(R.string.label_delete_favorite)
    else
        stringResource(R.string.label_add_favorite)
    Box(
        modifier = modifier
            .padding(16.dp)
            .background(white, RoundedCornerShape(50.dp))
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = message,
                        duration = SnackbarDuration.Short
                    )
                }
                onFavoriteClick()
            },
            modifier = Modifier
        ) {
            Icon(
                imageVector =
                if (isFavorite) Icons.Default.Favorite
                else Icons.Default.FavoriteBorder,
                tint = Color.Red,
                contentDescription = stringResource(R.string.label_favorites),
            )
        }
    }
}