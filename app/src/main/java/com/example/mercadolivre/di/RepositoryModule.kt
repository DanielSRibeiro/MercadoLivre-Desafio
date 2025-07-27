package com.example.mercadolivre.di

import com.example.core.data.remote.AccessTokenRemoteRepository
import com.example.core.data.local.AccessTokenRepository
import com.example.core.data.local.FavoriteRepository
import com.example.core.data.local.LastSeenRepository
import com.example.core.data.remote.SearchRemoteRepository
import com.example.mercadolivre.data.repository.AccessTokenRepositoryImpl
import com.example.mercadolivre.data.datasource.LastSeenRepositoryImpl
import com.example.mercadolivre.data.repository.AccessTokenRemoteRepositoryImpl
import com.example.mercadolivre.data.repository.FavoriteRepositoryImpl
import com.example.mercadolivre.data.repository.SearchRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAccessTokenRemoteRepository(
        repository: AccessTokenRemoteRepositoryImpl
    ): AccessTokenRemoteRepository

    @Binds
    fun bindGetTokenRepository(
        repository: AccessTokenRepositoryImpl
    ): AccessTokenRepository

    @Binds
    fun bindLastSeenRepository(
        repository: LastSeenRepositoryImpl
    ) : LastSeenRepository

    @Binds
    fun bindFavoriteRepository(
        repository: FavoriteRepositoryImpl
    ) : FavoriteRepository

    @Binds
    fun bindSearchRepository(
        repository: SearchRemoteRepositoryImpl
    ) : SearchRemoteRepository
}