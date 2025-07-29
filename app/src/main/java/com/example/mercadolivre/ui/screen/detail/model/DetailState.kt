package com.example.mercadolivre.ui.screen.detail.model

import com.example.core.domain.model.ProductResults

data class DetailState(
    val productResults: ProductResults? = null,
    val isFavorite: Boolean = false,
)