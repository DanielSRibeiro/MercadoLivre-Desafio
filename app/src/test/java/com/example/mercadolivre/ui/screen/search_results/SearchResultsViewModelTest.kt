package com.example.mercadolivre.ui.screen.search_results

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.example.core.domain.usecase.SearchRemoteUseCase
import com.example.mercadolivre.util.Constants.SEARCH_RESULT_ARGUMENT_KEY
import com.example.testing.MainCoroutineRule
import com.example.testing.model.ProductResultsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchResultsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val viewModel by lazy {
        SearchResultsViewModel(
            searchRemoteUseCase = searchRemoteUseCase,
            savedStateHandle = SavedStateHandle(
                mapOf(SEARCH_RESULT_ARGUMENT_KEY to "iphone")
            )
        )
    }

    @Mock
    private lateinit var searchRemoteUseCase: SearchRemoteUseCase

    private val fakePagingData = PagingData.from(
        listOf(
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone),
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.SecondIphone),
        )
    )

    @Test
    fun `should validate paging data object`() = runTest {
        // Arrange
        whenever(searchRemoteUseCase.invoke(any())).thenReturn(
            flow { emit(fakePagingData) }
        )

        //Act
        val result = viewModel.uiState.value.products.first()

        //Assert
        assertNotNull(result)
    }

    @Test(expected = RuntimeException::class)
    fun `should return exception when calling to the use case`() = runTest {
        // Arrange
        whenever(searchRemoteUseCase.invoke(any())).thenThrow(
            RuntimeException()
        )

        //Act
        viewModel
    }
}