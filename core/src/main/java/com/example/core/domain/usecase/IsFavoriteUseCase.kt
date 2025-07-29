package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.ProductResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<Boolean>
    data class Params(val productResults: ProductResults)
}

class IsFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : IsFavoriteUseCase {

    override suspend fun invoke(params: IsFavoriteUseCase.Params): Flow<Boolean> {
        val result = repository.isFavorite(params.productResults.id)
        return flow {
            emit(result != null)
        }.flowOn(Dispatchers.IO)
    }
}