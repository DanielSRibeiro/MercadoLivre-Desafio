package com.example.core.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.remote.SearchRemoteRepository
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.base.PagingUseCase
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface SearchRemoteUseCase {
    operator fun invoke(params: Params): Flow<PagingData<ProductResults>>
    data class Params(val q: String, val pagingConfig: PagingConfig)
}

class SearchRemoteUseCaseImpl @Inject constructor(
    private val repository: SearchRemoteRepository
) : PagingUseCase<SearchRemoteUseCase.Params, ProductResults>(), SearchRemoteUseCase {

    override fun createFlowObservable(params: SearchRemoteUseCase.Params): Flow<PagingData<ProductResults>> {
        return try {
            val pagingSource = repository.searchProduct(params.q)

            return Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}