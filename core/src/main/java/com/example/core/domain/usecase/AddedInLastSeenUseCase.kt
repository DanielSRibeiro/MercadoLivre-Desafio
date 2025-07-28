package com.example.core.domain.usecase

import com.example.core.data.local.LastSeenRepository
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AddedInLastSeenUseCase {
    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>
    data class Params(val productResults: ProductResults)
}

class AddedInLastSeenUseCaseImpl @Inject constructor(
    private val lastSeenRepository: LastSeenRepository
) : UseCase<AddedInLastSeenUseCase.Params, Unit>(), AddedInLastSeenUseCase {

    override suspend fun doWork(params: AddedInLastSeenUseCase.Params): ResultStatus<Unit> {
        val added = lastSeenRepository.addedInLastSeen(params.productResults)
        return ResultStatus.Success(added)
    }
}