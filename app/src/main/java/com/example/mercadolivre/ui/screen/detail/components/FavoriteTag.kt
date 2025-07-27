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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.ui.theme.black
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
    Box(
        modifier = modifier
            .padding(16.dp)
            .background(white, RoundedCornerShape(50.dp))
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    snackBarHostState
                        .showSnackbar(
                            message = "Snackbar",
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
                contentDescription = "Favorite",
            )
        }
    }
}