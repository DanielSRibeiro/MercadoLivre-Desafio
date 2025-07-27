package com.example.mercadolivre.data.datasource

import com.example.core.data.remote.SearchRemoteDataSource
import com.example.core.domain.ErrorStates
import com.example.core.domain.usecase.base.ResultStatus
import com.example.mercadolivre.data.remote.service.MLService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val service: MLService
) : SearchRemoteDataSource {

    override suspend fun searchProduct(q: String): ResultStatus<String> {
        val response = service.searchProduct(q)

        if (response.isSuccessful && response.body() != null) {
            return ResultStatus.Success(response.body()!!.results[3].name)
        } else {
            var message = response.code().toString()
            if (response.body() != null) {
                message += "- ${response.body()}"
            }
            if (response.message().isNotBlank()) {
                message += "- ${response.message()}"
            }

            return when (response.code()) {
                401 -> ResultStatus.Failure(ErrorStates.Unauthorized, message)
                else -> ResultStatus.Failure(ErrorStates.Fail, message)
            }
        }

    }
}