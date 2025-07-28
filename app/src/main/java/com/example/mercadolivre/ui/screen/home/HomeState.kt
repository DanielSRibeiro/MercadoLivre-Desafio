package com.example.mercadolivre.ui.screen.home

import com.example.core.domain.model.ProductResults

data class HomeState(
    val lastSeenProductData: List<ProductResults> = emptyList(),
    val favoritesProductData: List<ProductResults> = emptyList(),
    val isNetworkError: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = true,
)