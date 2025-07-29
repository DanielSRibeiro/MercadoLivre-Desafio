package com.example.core.domain.usecase

import com.example.core.data.remote.AccessTokenRemoteRepository
import com.example.core.domain.usecase.base.ResultStatus
import com.example.testing.model.AccessTokenFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAccessTokenUseCaseImplTest {

    @Mock
    private lateinit var repository: AccessTokenRemoteRepository

    private lateinit var useCase: GetAccessTokenUseCase

    private val token = AccessTokenFactory().create(AccessTokenFactory.TokenIsExpired.No)

    @Before
    fun setup() {
        useCase = GetAccessTokenUseCaseImpl(repository)
    }

    @Test
    fun `should return access token successfully`() = runTest {
        // Arrange
        val expected = ResultStatus.Success(token)
        whenever(repository.getAccessToken()).thenReturn(expected)

        // Act
        val result = useCase(GetAccessTokenUseCase.Params(Unit)).toList()

        // Assert
        assertEquals(ResultStatus.Loading, result[0])
        assertEquals(expected, result[1])
    }
}