package com.example.mercadolivre.ui.components.commo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AsyncImageUrl(
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    imageUrl: String,
    crossFadeEnabled: Boolean = true,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(crossFadeEnabled)
            .build(),
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}