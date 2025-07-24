package com.example.core.domain.usecase.base

import com.example.core.domain.ErrorStates

sealed class ResultStatus<out T> {
    data object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Failure(val status: ErrorStates, val message: String) : ResultStatus<Nothing>()
}