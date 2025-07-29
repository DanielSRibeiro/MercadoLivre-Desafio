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
class AddedInProductUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: FavoriteRepository

    private lateinit var useCase: AddedInProductUseCase

    private val product = ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone)

    @Before
    fun setUp() {
        useCase = AddedInProductUseCaseImpl(repository)
    }

    @Test
    fun `should return success when product is added in favorite`() = runTest {
        // Arrange
        whenever(repository.addedInProduct(any())).thenReturn(Unit)

        // Act
        val emissions = useCase.invoke(AddedInProductUseCase.Params(product)).toList()

        // Assert
        assertEquals(ResultStatus.Loading, emissions[0])
        assertEquals(ResultStatus.Success(Unit), emissions[1])
    }
}