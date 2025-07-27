package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.data.local.LastSeenRepository
import com.example.core.domain.model.Product
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DeleteProductUseCase {
    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>
    data class Params(val product: Product)
}

class DeleteProductUseCaseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase<DeleteProductUseCase.Params, Unit>(), DeleteProductUseCase {

    override suspend fun doWork(params: DeleteProductUseCase.Params): ResultStatus<Unit> {
        val added = repository.deleteProduct(params.product)
        return ResultStatus.Success(added)
    }
}