package com.example.mercadolivre.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mercadolivre.ui.screen.home.HomeScreen
import com.example.mercadolivre.ui.screen.home.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen()
        }
    }
}