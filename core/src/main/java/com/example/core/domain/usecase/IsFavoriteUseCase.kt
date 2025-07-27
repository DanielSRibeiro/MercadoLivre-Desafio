package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.Product
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IsFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Boolean>>
    data class Params(val product: Product)
}

class IsFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase<IsFavoriteUseCase.Params, Boolean>(), IsFavoriteUseCase {

    override suspend fun doWork(params: IsFavoriteUseCase.Params): ResultStatus<Boolean> {
        val result = repository.isFavorite(params.product.id)
        return ResultStatus.Success(result != null)
    }

}