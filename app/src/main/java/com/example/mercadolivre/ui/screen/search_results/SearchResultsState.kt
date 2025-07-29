package com.example.mercadolivre.ui.screen.search_results

import androidx.paging.PagingData
import com.example.core.domain.model.ProductResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchResultsState(
    val products: Flow<PagingData<ProductResults>> = emptyFlow()
)