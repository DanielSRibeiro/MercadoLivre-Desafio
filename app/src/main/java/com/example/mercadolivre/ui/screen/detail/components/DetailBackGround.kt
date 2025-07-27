package com.example.mercadolivre.ui.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.ui.components.commo.AsyncImageUrl

@Composable
fun DetailBackGround(
    snackBarHostState: SnackbarHostState,
    picturesList: List<String>,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { picturesList.size })

    Box(Modifier.wrapContentHeight()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) { page ->
            AsyncImageUrl(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                imageUrl = picturesList[page],
            )

        }
        PagerIndicatorTag(
            modifier = Modifier.align(Alignment.TopStart),
            text = "${pagerState.currentPage + 1}/${picturesList.size}"
        )
        FavoriteTag(
            modifier = Modifier.align(Alignment.TopEnd),
            snackBarHostState = snackBarHostState,
            isFavorite = isFavorite,
            onFavoriteClick = onFavoriteClick
        )
    }
    PagerIndicator(picturesList.size, pagerState.currentPage)
}

@Composable
fun PagerIndicator(pageCount: Int, currentPageIndex: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color = if (currentPageIndex == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}