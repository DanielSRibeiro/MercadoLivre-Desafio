package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DeleteProductUseCase {
    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>
    data class Params(val productResults: ProductResults)
}

class DeleteProductUseCaseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase<DeleteProductUseCase.Params, Unit>(), DeleteProductUseCase {

    override suspend fun doWork(params: DeleteProductUseCase.Params): ResultStatus<Unit> {
        val added = repository.deleteProduct(params.productResults)
        return ResultStatus.Success(added)
    }
}