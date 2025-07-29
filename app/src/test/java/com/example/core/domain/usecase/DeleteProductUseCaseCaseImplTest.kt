package com.example.core.domain.usecase

import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.ProductResultsFactory
import com.nhaarman.mockitokotlin2.any
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
class DeleteProductUseCaseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: FavoriteRepository

    private lateinit var useCase: DeleteProductUseCase

    private val product = ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone)

    @Before
    fun setUp() {
        useCase = DeleteProductUseCaseCaseImpl(repository)
    }


    @Test
    fun `should return success when product is delete in favorite`() = runTest {
        // Arrange
        whenever(repository.deleteProduct(any())).thenReturn(Unit)

        // Act
        val emissions = useCase.invoke(DeleteProductUseCase.Params(product)).toList()

        // Assert
        assertEquals(ResultStatus.Loading, emissions[0])
        assertEquals(ResultStatus.Success(Unit), emissions[1])
    }
}