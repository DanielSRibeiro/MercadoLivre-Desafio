package com.example.mercadolivre.di

import android.content.Context
import androidx.room.Room
import com.example.mercadolivre.data.local.MlDatabase
import com.example.mercadolivre.data.local.dao.MlDao
import com.example.mercadolivre.util.Constants
import com.example.mercadolivre.util.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MlDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    fun provideMlDao(database: MlDatabase): MlDao {
        return database.mlDao()
    }
}