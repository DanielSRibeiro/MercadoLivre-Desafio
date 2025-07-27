package com.example.mercadolivre.ui.screen.detail

import com.example.core.domain.model.Product

sealed class DetailEvent {
    data class addedInLastSeen(val product: Product) : DetailEvent()
    data class checkedFavorite(val id: String) : DetailEvent()
    data class addFavorite(val id: String) : DetailEvent()
    data class removeFavorite(val id: String) : DetailEvent()
}
