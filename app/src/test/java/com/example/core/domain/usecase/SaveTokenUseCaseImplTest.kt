package com.example.core.domain.usecase

import com.example.core.data.local.AccessTokenRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.AccessTokenFactory
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveTokenUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: AccessTokenRepository

    private lateinit var useCase: SaveTokenUseCase

    private val accessToken = AccessTokenFactory().create(AccessTokenFactory.TokenIsExpired.No)

    @Before
    fun setUp() {
        useCase = SaveTokenUseCaseImpl(repository)
    }

    @Test
    fun `should save token when invoked`() = runTest {
        // Act
        useCase.invoke(params = SaveTokenUseCase.Params(accessToken))

        // Assert
        verify(repository, times(1)).saveToken(accessToken)
    }
}