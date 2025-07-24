package com.example.mercadolivre.di

import com.example.core.domain.usecase.GetAccessTokenUseCase
import com.example.core.domain.usecase.GetAccessTokenUseCaseImpl
import com.example.core.domain.usecase.GetTokenUseCase
import com.example.core.domain.usecase.GetTokenUseCaseImpl
import com.example.core.domain.usecase.SaveTokenUseCase
import com.example.core.domain.usecase.SaveTokenUseCaseImpl
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
}