package com.example.mercadolivre.framework.di

import com.example.core.domain.usecase.AddedInLastSeenUseCase
import com.example.core.domain.usecase.AddedInLastSeenUseCaseImpl
import com.example.core.domain.usecase.AddedInProductUseCase
import com.example.core.domain.usecase.AddedInProductUseCaseImpl
import com.example.core.domain.usecase.DeleteProductUseCase
import com.example.core.domain.usecase.DeleteProductUseCaseCaseImpl
import com.example.core.domain.usecase.GetAccessTokenUseCase
import com.example.core.domain.usecase.GetAccessTokenUseCaseImpl
import com.example.core.domain.usecase.GetAllFavoritesUseCase
import com.example.core.domain.usecase.GetAllFavoritesUseCaseImpl
import com.example.core.domain.usecase.GetLastSeenUseCase
import com.example.core.domain.usecase.GetLastSeenUseCaseImpl
import com.example.core.domain.usecase.GetTokenUseCase
import com.example.core.domain.usecase.GetTokenUseCaseImpl
import com.example.core.domain.usecase.IsFavoriteUseCase
import com.example.core.domain.usecase.IsFavoriteUseCaseImpl
import com.example.core.domain.usecase.SaveTokenUseCase
import com.example.core.domain.usecase.SaveTokenUseCaseImpl
import com.example.core.domain.usecase.SearchRemoteUseCase
import com.example.core.domain.usecase.SearchRemoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetAccessTokenUseCase(
        getAccessTokenUseCaseImpl: GetAccessTokenUseCaseImpl
    ): GetAccessTokenUseCase

    @Binds
    fun bindSaveTokenUseCase(
        saveTokenUseCaseImpl: SaveTokenUseCaseImpl
    ): SaveTokenUseCase

    @Binds
    fun bindGetTokenUseCase(
        getTokenUseCaseImpl: GetTokenUseCaseImpl
    ): GetTokenUseCase

    @Binds
    fun bindAddedInLastSeenUseCase(
        addedInLastSeenUseCaseImpl: AddedInLastSeenUseCaseImpl
    ): AddedInLastSeenUseCase

    @Binds
    fun bindGetLastSeenUseCase(
        getLastSeenUseCaseImpl: GetLastSeenUseCaseImpl
    ): GetLastSeenUseCase

    @Binds
    fun bindIsFavoriteUseCase(
        isFavoriteUseCaseImpl: IsFavoriteUseCaseImpl
    ): IsFavoriteUseCase

    @Binds
    fun bindAddedInProductUseCase(
        addedInProductUseCaseImpl: AddedInProductUseCaseImpl
    ): AddedInProductUseCase

    @Binds
    fun bindDeleteProductUseCase(
        deleteProductUseCaseImpl: DeleteProductUseCaseCaseImpl
    ) : DeleteProductUseCase

    @Binds
    fun bindGetAllFavoritesUseCase(
        getAllFavoritesUseCaseImpl: GetAllFavoritesUseCaseImpl
    ) : GetAllFavoritesUseCase

    @Binds
    fun bindSearchRemoteUseCase(
        searchRemoteUseCaseImpl: SearchRemoteUseCaseImpl,
    ) : SearchRemoteUseCase
}