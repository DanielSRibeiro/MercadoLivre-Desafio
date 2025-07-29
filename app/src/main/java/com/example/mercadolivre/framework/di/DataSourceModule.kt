package com.example.mercadolivre.framework.di

import com.example.core.data.local.AccessTokenDataSource
import com.example.core.data.local.FavoriteDataSource
import com.example.core.data.local.LastSeenDataSource
import com.example.core.data.local.PreferenceDataSource
import com.example.core.data.remote.AccessTokenRemoteDataSource
import com.example.core.data.remote.SearchRemoteDataSource
import com.example.mercadolivre.framework.data.repository.AccessTokenDataSourceImpl
import com.example.mercadolivre.framework.data.datasource.LastSeenDataSourceImpl
import com.example.mercadolivre.framework.data.datasource.AccessTokenRemoteDataSourceImpl
import com.example.mercadolivre.framework.data.datasource.FavoriteDataSourceImpl
import com.example.mercadolivre.framework.data.datasource.PreferenceHelperImpl
import com.example.mercadolivre.framework.data.datasource.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindMLDataSource(
        mLDataSourceImpl: AccessTokenRemoteDataSourceImpl
    ): AccessTokenRemoteDataSource

    @Binds
    fun bindSaveTokenDataSource(
        accessTokenDataSourceImpl: AccessTokenDataSourceImpl
    ): AccessTokenDataSource

    @Binds
    fun bindLastSeenDataSource(
        lastSeenDataSourceImpl: LastSeenDataSourceImpl
    ) : LastSeenDataSource

    @Binds
    fun bindFavoriteDataSource(
        favoriteDataSourceImpl: FavoriteDataSourceImpl
    ) : FavoriteDataSource

    @Binds
    fun bindSearchDataSource(
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl
    ) : SearchRemoteDataSource

    @Binds
    fun bindPreferenceDataSource(
        preferenceHelperImpl: PreferenceHelperImpl
    ) : PreferenceDataSource
}