package com.example.mercadolivre.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.PicturesResponse
import com.example.core.domain.model.Product
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.AddedInLastSeenUseCase
import com.example.core.domain.usecase.AddedInProductUseCase
import com.example.core.domain.usecase.DeleteProductUseCase
import com.example.core.domain.usecase.IsFavoriteUseCase
import com.example.core.domain.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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
    private val mock = Product(
        id = "MLB3647888693",
        data = ProductResults(
            title = "Café Torrado E Moído Extraforte 250g 3 Corações",
            description = "descrição",
            price = 16.82,
            basePrice = 22.82,
            thumbnail = "",
            pictures = arrayListOf(
                PicturesResponse(
                    id = "604836-MLU75811690120_042024",
                    url = "https://http2.mlstatic.com/D_604836-MLU75811690120_042024-I.jpg"
                ),
                PicturesResponse(
                    id = "844581-MLU72748497151_112023",
                    url = "https://http2.mlstatic.com/D_604836-MLU75811690120_042024-O.jpg"
                ),
                PicturesResponse(
                    id = "866617-MLU75401258048_042024",
                    url = "https://http2.mlstatic.com/D_866617-MLU75401258048_042024-O.jpg"
                ),
            ),
        )
    )

    init {
        viewModelScope.launch {
            addedInLastSeen(mock)
        }
    }

    private suspend fun addedInLastSeen(product: Product) {
        addedInLastSeenUseCase.invoke(
            params = AddedInLastSeenUseCase.Params(product)
        ).collect {
            _uiState.value = _uiState.value.copy(product = product)
            isFavorite(product)
        }
    }

    private suspend fun isFavorite(product: Product) {
        isFavoriteUseCase.invoke(
            params = IsFavoriteUseCase.Params(
                product
            )
        ).collectLatest {
            when (it) {
                is ResultStatus.Success -> {
                    val isFavorite = it.data
                    _uiState.value = _uiState.value.copy(
                        isFavorite = isFavorite,
                        isNetworkError = false,
                        isSuccess = true,
                        isError = false,
                        isLoading = false
                    )
                }

                is ResultStatus.Failure -> {

                }

                ResultStatus.Loading -> {

                }
            }
        }
    }

    fun addInFavorite() {
        viewModelScope.launch {
            uiState.value.product?.let {
                if (uiState.value.isFavorite) deleteProductSeen(it)
                else addInProductSeen(it)
            }
        }
    }

    private suspend fun addInProductSeen(product: Product) {
        addedInProductUseCase.invoke(params = AddedInProductUseCase.Params(product))
            .collectLatest {
                when (it) {
                    is ResultStatus.Success -> _uiState.value =
                        _uiState.value.copy(isFavorite = true)

                    is ResultStatus.Failure -> Log.d(
                        "AddInProductSeenTag",
                        "addInProductSeen: ${it.status} /n ${it.message}"
                    )

                    ResultStatus.Loading -> {}
                }
            }
    }

    private suspend fun deleteProductSeen(product: Product) {
        deleteProductUseCase.invoke(
            params = DeleteProductUseCase.Params(
                product
            )
        ).collect {
            when (it) {
                is ResultStatus.Success -> _uiState.value = _uiState.value.copy(isFavorite = false)
                is ResultStatus.Failure -> Log.d(
                    "DeleteProductSeenTag",
                    "deleteProductSeen: ${it.status} /n ${it.message}"
                )

                ResultStatus.Loading -> {}
            }
        }
    }
}

data class DetailState(
    val product: Product? = null,
    val isFavorite: Boolean = false,
    val isNetworkError: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
)