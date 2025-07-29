package com.example.mercadolivre.framework.paging

import androidx.paging.PagingSource
import com.example.core.data.remote.SearchRemoteDataSource
import com.example.core.domain.model.ProductResults
import com.example.testing.MainCoroutineRule
import com.example.testing.model.PagingSourceFactory
import com.example.testing.model.ProductResultsFactory
import com.example.testing.model.ProductsFactory
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchPagingSourceTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var dataSource: SearchRemoteDataSource

    private lateinit var searchPagingSource: SearchPagingSource

    private val query = "iphone"

    private val products = ProductsFactory().create()

    @Before
    fun setUp() {
        searchPagingSource = SearchPagingSource(
            query = query,
            dataSource = dataSource
        )
    }


    @Test
    fun `should return a success load result when load is called`() = runTest {
        // Arrange
        whenever(dataSource.searchProduct(any(), any(), any()))
            .thenReturn(products)

        // Act
        val result = searchPagingSource.load(
            PagingSource.LoadParams.Refresh(0, loadSize = 2, false)
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = products.results,
                prevKey = null,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runTest {
        // Arrange
        val exception = RuntimeException()
        whenever(dataSource.searchProduct(any(), any(), any()))
            .thenThrow(exception)

        // Act
        val result = searchPagingSource.load(
            PagingSource.LoadParams.Refresh(0, loadSize = 2, false)
        )

        // Assert
        assertEquals(
            PagingSource.LoadResult.Error<Int, ProductResults>(exception),
            result
        )
    }
}