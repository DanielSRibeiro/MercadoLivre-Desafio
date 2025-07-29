package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.ProductResultsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IsFavoriteUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: FavoriteRepository

    private lateinit var useCase: IsFavoriteUseCase

    private val mockProduct =
        ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone)

    @Before
    fun setUp() {
        useCase = IsFavoriteUseCaseImpl(repository)
    }

    @Test
    fun `should return true when product is favorite`() = runTest {
        // Arrange
        whenever(repository.isFavorite(any())).thenReturn(mockProduct)

        // Act
        val result = useCase(IsFavoriteUseCase.Params(mockProduct)).first()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `should return false when product is not favorite`() = runTest {
        // Arrange
        whenever(repository.isFavorite(any())).thenReturn(null)

        // Act
        val result = useCase(IsFavoriteUseCase.Params(mockProduct)).first()

        // Assert
        assertFalse(result)
    }
}