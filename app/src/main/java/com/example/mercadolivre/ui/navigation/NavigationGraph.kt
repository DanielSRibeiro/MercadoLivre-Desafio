package com.example.mercadolivre.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mercadolivre.ui.screen.home.HomeScreen
import com.example.mercadolivre.ui.screen.home.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.mercadolivre.ui.screen.search.SearchScreen
import com.example.mercadolivre.ui.screen.detail.DetailScreen
import com.example.mercadolivre.ui.screen.detail.DetailViewModel
import com.example.mercadolivre.ui.screen.detail.ProductResultsDetail
import com.example.mercadolivre.ui.screen.search.SearchViewModel
import com.example.mercadolivre.ui.screen.search_results.SearchResultsScreen
import com.example.mercadolivre.ui.screen.search_results.SearchResultsViewModel
import com.example.mercadolivre.util.Constants.DETAIL_ARGUMENT_KEY

@Composable
fun NavigationGraph(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) { navBackStackEntry ->
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            val lifecycle = navBackStackEntry.lifecycle
            DisposableEffect(lifecycle) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        viewModel.retry()
                    }
                }
                lifecycle.addObserver(observer)
                onDispose {
                    lifecycle.removeObserver(observer)
                }
            }

            HomeScreen(
                uiState = uiState,
                onNavigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        DETAIL_ARGUMENT_KEY,
                        it
                    )
                    navController.navigate(Screens.Detail.route)
                },
                retry = viewModel::retry,
                onNavigateToSearch = {
                    navController.navigate(Screens.Search.route)
                }
            )
        }
        composable(route = Screens.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            val saveQuery = viewModel::saveQuery
            SearchScreen(
                uiState = uiState,
                onQueryChange = viewModel::event,
                popBackStack = { navController.popBackStack() },
                onSearch = {
                    saveQuery()
                    navController.navigate(Screens.SearchResults.passQuery(it)) {
                        popUpTo(Screens.SearchResults.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = Screens.SearchResults.route) {
            val viewModel: SearchResultsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            SearchResultsScreen(
                uiState = uiState,
                navController = navController,
            )
        }
        composable(route = Screens.Detail.route) {
            val productResultsDetail = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<ProductResultsDetail>(DETAIL_ARGUMENT_KEY)

            val viewModel: DetailViewModel = hiltViewModel()
            productResultsDetail?.let {
                viewModel.initWithProduct(it)
            }
            val uiState by viewModel.uiState.collectAsState()
            DetailScreen(
                snackBarHostState = snackBarHostState,
                uiState = uiState,
                onFavoriteClick = viewModel::addInFavorite
            )
        }
    }
}