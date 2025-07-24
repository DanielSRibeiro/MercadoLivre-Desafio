package com.example.core.domain.usecase.base

import com.example.core.domain.ErrorStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

abstract class UseCase<in P, out R> {

    operator fun invoke(params: P): Flow<ResultStatus<R>> = flow {
        emit(ResultStatus.Loading)
        emit(doWork(params))
    }.catch { throwable ->
        if (throwable is UnknownHostException) {
            emit(ResultStatus.Failure(ErrorStates.NoInternet, throwable.message ?: throwable.toString()))
        } else if (throwable is Exception) {
            emit(ResultStatus.Failure(ErrorStates.Exception, throwable.message ?: throwable.toString()))
        }
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun doWork(params: P): ResultStatus<R>
}