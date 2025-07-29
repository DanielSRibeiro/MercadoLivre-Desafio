package com.example.mercadolivre.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.AddedInLastSeenUseCase
import com.example.core.domain.usecase.AddedInProductUseCase
import com.example.core.domain.usecase.DeleteProductUseCase
import com.example.core.domain.usecase.IsFavoriteUseCase
import com.example.mercadolivre.ui.screen.detail.model.DetailState
import com.example.mercadolivre.ui.screen.detail.model.ProductResultsDetail
import com.example.mercadolivre.ui.screen.detail.model.toProductResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addedInLastSeenUseCase: AddedInLastSeenUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addedInProductUseCase: AddedInProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    fun initWithProduct(product: ProductResultsDetail) {
        if (_uiState.value.productResults == null) {
            viewModelScope.launch {
                addedInLastSeen(product.toProductResults())
            }
        }
    }

    private suspend fun addedInLastSeen(productResults: ProductResults) {
        addedInLastSeenUseCase.invoke(
            params = AddedInLastSeenUseCase.Params(productResults)
        ).collect {
            _uiState.value = _uiState.value.copy(productResults = productResults)
            isFavorite(productResults)
        }
    }

    private suspend fun isFavorite(productResults: ProductResults) {
        isFavoriteUseCase.invoke(
            params = IsFavoriteUseCase.Params(
                productResults
            )
        ).collect { isFavorite ->
            _uiState.value = _uiState.value.copy(isFavorite = isFavorite)
        }
    }

    fun addInFavorite() {
        viewModelScope.launch {
            uiState.value.productResults?.let {
                if (uiState.value.isFavorite) deleteProductSeen(it)
                else addInProductSeen(it)
            }
        }
    }

    private suspend fun addInProductSeen(productResults: ProductResults) {
        addedInProductUseCase.invoke(
            params = AddedInProductUseCase.Params(productResults)
        ).collect {
            _uiState.value = _uiState.value.copy(isFavorite = true)
        }
    }

    private suspend fun deleteProductSeen(productResults: ProductResults) {
        deleteProductUseCase.invoke(
            params = DeleteProductUseCase.Params(
                productResults
            )
        ).collect {
            _uiState.value = _uiState.value.copy(isFavorite = false)
        }
    }
}