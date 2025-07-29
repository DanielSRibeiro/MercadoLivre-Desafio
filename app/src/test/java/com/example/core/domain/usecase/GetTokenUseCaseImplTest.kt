package com.example.core.domain.usecase

import com.example.core.data.local.AccessTokenRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.AccessTokenFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTokenUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: AccessTokenRepository

    private lateinit var useCase: GetTokenUseCase

    private val accessToken = AccessTokenFactory().create(AccessTokenFactory.TokenIsExpired.No)

    @Before
    fun setup() {
        useCase = GetTokenUseCaseImpl(repository)
    }

    @Test
    fun `should return token from repository`() {
        // Arrange
        whenever(repository.getToken()).thenReturn(accessToken)

        // Act
        val result = useCase()

        // Assert
        assertEquals(accessToken, result)
    }

    @Test
    fun `should return null when no token is saved`() {
        // Arrange
        whenever(repository.getToken()).thenReturn(null)

        // Act
        val result = useCase()

        // Assert
        assertEquals(null, result)
    }
}