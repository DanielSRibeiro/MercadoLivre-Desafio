package com.example.core.domain.usecase

import com.example.core.data.local.LastSeenRepository
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetLastSeenUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<List<ProductResults>>>
    data class Params(val params: Unit)
}

class GetLastSeenUseCaseImpl @Inject constructor(
    private val lastSeenRepository: LastSeenRepository
) : UseCase<GetLastSeenUseCase.Params, List<ProductResults>>(), GetLastSeenUseCase {

    override suspend fun doWork(params: GetLastSeenUseCase.Params): ResultStatus<List<ProductResults>> {
        return ResultStatus.Success(lastSeenRepository.getLastSeen())
    }
}