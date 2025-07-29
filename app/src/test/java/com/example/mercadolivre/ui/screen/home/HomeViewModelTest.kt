package com.example.mercadolivre.ui.screen.home

import com.example.core.domain.ErrorStates
import com.example.core.domain.usecase.GetAccessTokenUseCase
import com.example.core.domain.usecase.GetAllFavoritesUseCase
import com.example.core.domain.usecase.GetLastSeenUseCase
import com.example.core.domain.usecase.GetTokenUseCase
import com.example.core.domain.usecase.SaveTokenUseCase
import com.example.core.domain.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.AccessTokenFactory
import com.example.testing.model.ProductResultsFactory
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
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getAccessTokenUseCase: GetAccessTokenUseCase

    @Mock
    private lateinit var saveTokenUseCase: SaveTokenUseCase

    @Mock
    private lateinit var getTokenUseCase: GetTokenUseCase

    @Mock
    private lateinit var getLastSeenUseCase: GetLastSeenUseCase

    @Mock
    private lateinit var getAllFavoritesUseCase: GetAllFavoritesUseCase

    private val productResultsFake = ResultStatus.Success(
        listOf(
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone),
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.SecondIphone),
        )
    )
    private val yesExpired = AccessTokenFactory().create(AccessTokenFactory.TokenIsExpired.Yes)
    private val noExpired = AccessTokenFactory().create(AccessTokenFactory.TokenIsExpired.No)

    private val viewmodel by lazy {
        HomeViewModel(
            getAccessTokenUseCase = getAccessTokenUseCase,
            saveTokenUseCase = saveTokenUseCase,
            getTokenUseCase = getTokenUseCase,
            getLastSeenUseCase = getLastSeenUseCase,
            getAllFavoritesUseCase = getAllFavoritesUseCase
        )
    }

    @Test
    fun `should calling get Token when the token has expired and return the list of last seen and favorites`() =
        runTest {
            // Arrange
            whenever(getTokenUseCase.invoke()).thenReturn(yesExpired)
            whenever(getAccessTokenUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(yesExpired))
            )
            whenever(getLastSeenUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))
            whenever(getAllFavoritesUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))

            // Act
            viewmodel
            val uiState = viewmodel.uiState.value

            // Assert
            assertTrue(uiState.isSuccess)
            assertFalse(uiState.isLoading)
            assertFalse(uiState.isNetworkError)
            assertFalse(uiState.isError)

            assertEquals(productResultsFake.data, uiState.lastSeenProductData)
            assertEquals(productResultsFake.data, uiState.favoritesProductData)
        }

    @Test
    fun `should error network when the token has expired`() = runTest {
        // Arrange
        whenever(getTokenUseCase.invoke()).thenReturn(yesExpired)
        whenever(getAccessTokenUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Failure(ErrorStates.NoInternet, "No Internet")))
        whenever(getLastSeenUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))
        whenever(getAllFavoritesUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))

        // Act
        viewmodel
        val uiState = viewmodel.uiState.value

        // Assert
        assertTrue(uiState.isNetworkError)
        assertFalse(uiState.isSuccess)
        assertFalse(uiState.isLoading)
        assertFalse(uiState.isError)
    }

    @Test
    fun `should error when the token has expired`() = runTest {
        // Arrange
        whenever(getTokenUseCase.invoke()).thenReturn(yesExpired)
        whenever(getAccessTokenUseCase.invoke(any()))
            .thenReturn(flowOf(ResultStatus.Failure(ErrorStates.Exception, "error")))
        whenever(getLastSeenUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))
        whenever(getAllFavoritesUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))

        // Act
        viewmodel
        val uiState = viewmodel.uiState.value

        // Assert
        assertTrue(uiState.isError)
        assertFalse(uiState.isNetworkError)
        assertFalse(uiState.isSuccess)
        assertFalse(uiState.isLoading)
    }

    @Test
    fun `should loading when the token has expired and return error when calling to last seen and favorites`() =
        runTest {
            // Arrange
            whenever(getTokenUseCase.invoke()).thenReturn(yesExpired)
            whenever(getAccessTokenUseCase.invoke(any()))
                .thenReturn(flowOf(ResultStatus.Loading))
            whenever(getLastSeenUseCase.invoke(any())).thenReturn(
                flowOf(
                    ResultStatus.Failure(ErrorStates.Exception, "error")
                )
            )
            whenever(getAllFavoritesUseCase.invoke(any())).thenReturn(
                flowOf(
                    ResultStatus.Failure(ErrorStates.Exception, "error")
                )
            )

            // Act
            viewmodel
            val uiState = viewmodel.uiState.value

            // Assert
            assertTrue(uiState.isLoading)
            assertFalse(uiState.isError)
            assertFalse(uiState.isNetworkError)
            assertFalse(uiState.isSuccess)

            assertTrue(uiState.lastSeenProductData.isEmpty())
            assertTrue(uiState.favoritesProductData.isEmpty())
        }



    @Test
    fun `should not calling token`() =
        runTest {
            // Arrange
            whenever(getTokenUseCase.invoke()).thenReturn(noExpired)
            whenever(getLastSeenUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))
            whenever(getAllFavoritesUseCase.invoke(any())).thenReturn(flowOf(productResultsFake))

            // Act
            viewmodel
            val uiState = viewmodel.uiState.value

            // Assert
            assertFalse(uiState.isLoading)
            assertEquals(productResultsFake.data, uiState.lastSeenProductData)
            assertEquals(productResultsFake.data, uiState.favoritesProductData)
        }

}