package com.example.mercadolivre.di

import com.example.core.data.local.AccessTokenDataSource
import com.example.mercadolivre.data.local.AccessTokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindMLDataSource(mLDataSourceImpl: com.example.mercadolivre.data.remote.AccessTokenRemoteDataSourceImpl): com.example.core.data.remote.AccessTokenRemoteDataSource

    @Binds
    fun bindSaveTokenDataSource(accessTokenDataSourceImpl: AccessTokenDataSourceImpl): AccessTokenDataSource
}