package com.example.core.domain.usecase

import com.example.core.data.remote.SearchRemoteRepository
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchRemoteUseCase {
    operator fun invoke(params: Params) : Flow<ResultStatus<String>>
    data class Params(val q: String)
}

class SearchRemoteUseCaseImpl @Inject constructor(
    private val repository: SearchRemoteRepository
) : UseCase<SearchRemoteUseCase.Params, String>(), SearchRemoteUseCase {

    override suspend fun doWork(params: SearchRemoteUseCase.Params): ResultStatus<String> {
        return repository.searchProduct(params.q)
    }

}