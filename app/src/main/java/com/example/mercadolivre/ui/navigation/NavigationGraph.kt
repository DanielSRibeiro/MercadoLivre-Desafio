package com.example.mercadolivre.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mercadolivre.ui.screen.home.HomeScreen
import com.example.mercadolivre.ui.screen.home.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mercadolivre.ui.screen.search.SearchScreen
import com.example.mercadolivre.ui.screen.detail.DetailScreen
import com.example.mercadolivre.ui.screen.detail.DetailViewModel
import com.example.mercadolivre.ui.screen.search.SearchViewModel
import com.example.mercadolivre.ui.screen.search_results.SearchResultsScreen
import com.example.mercadolivre.ui.screen.search_results.SearchResultsViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                uiState = uiState,
                onNavigateToDetail = {
                    navController.navigate(Screens.Detail.route)
                }
            )
        }
        composable(route = Screens.Detail.route) {
            val viewModel: DetailViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            DetailScreen(
                snackBarHostState = snackBarHostState,
                uiState = uiState,
                onFavoriteClick = viewModel::addInFavorite
            )
        }
        composable(route = Screens.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            SearchScreen(
                uiState = uiState,
                onQueryChange = viewModel::event,
                popBackStack = { navController.popBackStack() },
                onSearch = { navController.navigate(Screens.SearchResults.passQuery(it)) }
            )
        }
        composable(route = Screens.SearchResults.route) {
            val viewModel: SearchResultsViewModel = hiltViewModel()
            SearchResultsScreen()
        }
    }
}