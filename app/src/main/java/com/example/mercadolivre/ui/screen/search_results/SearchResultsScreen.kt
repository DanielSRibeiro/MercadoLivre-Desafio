package com.example.mercadolivre.ui.screen.search_results

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mercadolivre.ui.components.commo.AsyncImageUrl
import com.example.mercadolivre.ui.components.commo.LoadingView
import com.example.mercadolivre.ui.navigation.Screens
import com.example.mercadolivre.ui.screen.detail.model.toProductResultsDetail
import com.example.mercadolivre.util.Constants.DETAIL_ARGUMENT_KEY
import com.example.mercadolivre.ui.components.commo.DefaultError
import com.example.mercadolivre.ui.components.commo.EmptyScreen
import com.example.mercadolivre.ui.components.commo.ErrorNetworkScreen
import java.net.UnknownHostException

@Composable
fun SearchResultsScreen(
    uiState: SearchResultsState,
    navController: NavHostController
) {
    val paging = uiState.products.collectAsLazyPagingItems()

    when (val state = paging.loadState.refresh) {
        is LoadState.Error -> {
            if (state.error is UnknownHostException) {
                ErrorNetworkScreen(retry = paging::retry)
            } else {
                DefaultError(retry = paging::retry)
            }
        }

        LoadState.Loading -> {
            LoadingView()
        }

        is LoadState.NotLoading -> {

            if(paging.itemCount < 1){
                EmptyScreen()
                return
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(24.dp))
            ) {

                items(paging.itemCount) { index ->
                    paging[index]?.let {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(6.dp, RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.inverseSurface)
                                .clickable {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        DETAIL_ARGUMENT_KEY,
                                        it.toProductResultsDetail()
                                    )
                                    navController.navigate(Screens.Detail.route)
                                },
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                AsyncImageUrl(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop,
                                    imageUrl = it.pictures.firstOrNull()?.url.orEmpty(),
                                )
                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
                if (paging.loadState.append is LoadState.Loading) {
                    item { LoadingView() }
                }

                if (paging.loadState.append is LoadState.Error) {
                    item {
                        DefaultError(retry = paging::retry)
                    }
                }
            }
        }
    }
}