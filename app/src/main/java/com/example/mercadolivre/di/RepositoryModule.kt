package com.example.mercadolivre.di

import com.example.core.data.remote.AccessTokenRemoteRepository
import com.example.core.data.local.AccessTokenRepository
import com.example.mercadolivre.data.local.AccessTokenRepositoryImpl
import com.example.mercadolivre.data.remote.AccessTokenRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAccessTokenRemoteRepository(repository: AccessTokenRemoteRepositoryImpl): AccessTokenRemoteRepository

    @Binds
    fun bindGetTokenRepository(repository: AccessTokenRepositoryImpl): AccessTokenRepository
}