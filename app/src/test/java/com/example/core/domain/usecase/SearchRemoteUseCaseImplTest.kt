package com.example.core.domain.usecase

import androidx.paging.PagingConfig
import com.example.core.data.remote.SearchRemoteRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.PagingSourceFactory
import com.example.testing.model.ProductResultsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.first

@RunWith(MockitoJUnitRunner::class)
class SearchRemoteUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: SearchRemoteRepository

    private lateinit var useCase: SearchRemoteUseCase

    private val product = PagingSourceFactory().create(
        listOf(
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone),
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.SecondIphone)
        )
    )

    @Before
    fun setUp() {
        useCase = SearchRemoteUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            whenever(repository.searchProduct(any()))
                .thenReturn(product)

            val result = useCase
                .invoke(SearchRemoteUseCase.Params("iphone", PagingConfig(20)))

            verify(repository, times(1)).searchProduct("iphone")
            assertNotNull(result.first())
        }
}