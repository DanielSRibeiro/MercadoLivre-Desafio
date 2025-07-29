package com.example.core.domain.usecase

import com.example.core.data.local.LastSeenRepository
import com.example.core.domain.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.ProductResultsFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetLastSeenUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: LastSeenRepository

    private lateinit var useCase: GetLastSeenUseCase

    private val productResults = listOf(
        ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone),
        ProductResultsFactory().create(ProductResultsFactory.ProductFactory.SecondIphone),
    )

    @Before
    fun setup() {
        useCase = GetLastSeenUseCaseImpl(repository)
    }

    @Test
    fun `should return all last seen list successfully`() = runTest {
        // Arrange
        whenever(repository.getLastSeen()).thenReturn(productResults)

        // Act
        val result = useCase(GetLastSeenUseCase.Params(Unit)).toList()

        // Assert
        assertEquals(ResultStatus.Loading, result[0])
        assertEquals(ResultStatus.Success(productResults), result[1])
    }
}