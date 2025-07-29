package com.example.mercadolivre.ui.screen.detail

import com.example.core.domain.usecase.AddedInLastSeenUseCase
import com.example.core.domain.usecase.AddedInProductUseCase
import com.example.core.domain.usecase.DeleteProductUseCase
import com.example.core.domain.usecase.IsFavoriteUseCase
import com.example.core.domain.usecase.base.ResultStatus
import com.example.mercadolivre.ui.screen.detail.model.AttributeDetail
import com.example.mercadolivre.ui.screen.detail.model.PictureDetail
import com.example.mercadolivre.ui.screen.detail.model.ProductResultsDetail
import com.example.mercadolivre.ui.screen.detail.model.toProductResults
import com.example.testing.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var addedInLastSeenUseCase: AddedInLastSeenUseCase

    @Mock
    private lateinit var isFavoriteUseCase: IsFavoriteUseCase

    @Mock
    private lateinit var addedInProductUseCase: AddedInProductUseCase

    @Mock
    private lateinit var deleteProductUseCase: DeleteProductUseCase

    private val viewModel by lazy {
        DetailViewModel(
            addedInLastSeenUseCase = addedInLastSeenUseCase,
            isFavoriteUseCase = isFavoriteUseCase,
            addedInProductUseCase = addedInProductUseCase,
            deleteProductUseCase = deleteProductUseCase
        )
    }

    private val mockDetail = ProductResultsDetail(
        id = "MLB15542659",
        name = "iPhone 8",
        pictures = listOf(
            PictureDetail(
                id = "729014-MLA43694099452_102020",
                url = "https://mla-s2-p.mlstatic.com/729014-MLA43694099452_102020-F.jpg"
            ),
            PictureDetail(
                id = "639795-MLA43694219079_102020",
                url = "https://mla-s2-p.mlstatic.com/639795-MLA43694219079_102020-F.jpg"
            )
        ),
        attribute = listOf(
            AttributeDetail(
                name = "Marca",
                valueName = "Apple"
            ),
            AttributeDetail(
                name = "Linha",
                valueName = "iPhone 8"
            )
        ),
        keywords = "iphone",
        total = 20
    )

    @Test
    fun `should initialize UI state with favorite true when product is favorite`() = runTest {
        // arrange
        whenever(addedInLastSeenUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Success(Unit)))
        whenever(isFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(true))

        // act
        viewModel.initWithProduct(mockDetail)

        // assert
        val uiState = viewModel.uiState.value
        assertEquals(mockDetail.toProductResults(), uiState.productResults)
        assertTrue(uiState.isFavorite)

    }

    @Test
    fun `should add product to favorites when it is not favorite`() = runTest {
        // arrange
        whenever(addedInLastSeenUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Success(Unit)))
        whenever(isFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(false))
        whenever(addedInProductUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Success(Unit)))

        // act
        viewModel.initWithProduct(mockDetail)
        viewModel.addInFavorite()

        // assert
        val uiState = viewModel.uiState.value
        assertEquals(mockDetail.toProductResults(), uiState.productResults)
        assertTrue(uiState.isFavorite)
    }

    @Test
    fun `should remove product from favorites when it is favorite`() = runTest {
        // arrange
        whenever(addedInLastSeenUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Success(Unit)))
        whenever(isFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(true))
        whenever(deleteProductUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Success(Unit)))

        // act
        viewModel.initWithProduct(mockDetail)
        viewModel.addInFavorite()

        // assert
        val uiState = viewModel.uiState.value
        assertEquals(mockDetail.toProductResults(), uiState.productResults)
        assertFalse(uiState.isFavorite)
    }
}