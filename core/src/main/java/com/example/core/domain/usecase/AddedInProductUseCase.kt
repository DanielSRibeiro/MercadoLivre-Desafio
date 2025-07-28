package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AddedInProductUseCase {
    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>
    data class Params(val productResults: ProductResults)
}

class AddedInProductUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase<AddedInProductUseCase.Params, Unit>(), AddedInProductUseCase {

    override suspend fun doWork(params: AddedInProductUseCase.Params): ResultStatus<Unit> {
        val added = repository.addedInProduct(params.productResults)
        return ResultStatus.Success(added)
    }
}