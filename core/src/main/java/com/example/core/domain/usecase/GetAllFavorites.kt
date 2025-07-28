package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.ProductResults
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllFavoritesUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<List<ProductResults>>>
    data class Params(val params: Unit)
}

class GetAllFavoritesUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : UseCase<GetAllFavoritesUseCase.Params, List<ProductResults>>(), GetAllFavoritesUseCase {

    override suspend fun doWork(params: GetAllFavoritesUseCase.Params): ResultStatus<List<ProductResults>> {
        return ResultStatus.Success(favoriteRepository.getAllFavorites())
    }
}