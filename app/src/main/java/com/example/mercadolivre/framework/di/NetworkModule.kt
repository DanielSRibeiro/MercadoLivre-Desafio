package com.example.mercadolivre.framework.di

import com.example.core.domain.usecase.GetTokenUseCase
import com.example.mercadolivre.BuildConfig
import com.example.mercadolivre.framework.data.remote.service.AccessTokenService
import com.example.mercadolivre.framework.data.remote.interceptor.AccessTokenInterceptor
import com.example.mercadolivre.framework.data.remote.interceptor.MLInterceptor
import com.example.mercadolivre.framework.data.remote.service.MLService
import com.example.mercadolivre.framework.di.qualifier.AccessClient
import com.example.mercadolivre.framework.di.qualifier.MLClient
import com.example.mercadolivre.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideAccessTokenInterceptor(): AccessTokenInterceptor {
        return AccessTokenInterceptor()
    }

    @Provides
    fun provideMLInterceptor(
        getTokenUseCase: GetTokenUseCase
    ): MLInterceptor {
        return MLInterceptor(getTokenUseCase)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

    @Provides
    @AccessClient
    fun provideOkHttpClient(
        accessTokenInterceptor: AccessTokenInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(accessTokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @MLClient
    fun provideMLOkHttpClient(
        mLInterceptor: MLInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mLInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideService(
        gsonConverterFactory: GsonConverterFactory,
        @AccessClient okHttpClient: OkHttpClient
    ): AccessTokenService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(AccessTokenService::class.java)
    }

    @Provides
    fun provideMLService(
        gsonConverterFactory: GsonConverterFactory,
        @MLClient okHttpClient: OkHttpClient
    ): MLService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(MLService::class.java)
    }
}
