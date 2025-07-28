package com.example.mercadolivre.ui.screen.search_results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.SearchRemoteUseCase
import com.example.mercadolivre.util.Constants.SEARCH_RESULT_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.domain.model.ProductResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    private val searchRemoteUseCase: SearchRemoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchResultsState())
    val uiState = _uiState.asStateFlow()

    private val query = savedStateHandle.get<String>(SEARCH_RESULT_ARGUMENT_KEY)

    init {
        query?.let {
            val products = searchRemoteUseCase.invoke(
                SearchRemoteUseCase.Params(q = query, pagingConfig = pagingConfig())
            ).cachedIn(viewModelScope)

            _uiState.value = _uiState.value.copy(products = products)
        }
    }

    private fun pagingConfig(): PagingConfig =
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
        )
}


data class SearchResultsState(
    val products: Flow<PagingData<ProductResults>> = emptyFlow(),
    val isNetworkError: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
)