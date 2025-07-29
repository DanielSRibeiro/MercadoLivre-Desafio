package com.example.core.domain.usecase

import com.example.core.data.local.LastSeenRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.ProductResultsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddedInLastSeenUseCaseImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var lastSeenRepository: LastSeenRepository

    private lateinit var useCase: AddedInLastSeenUseCase

    @Before
    fun setUp() {
        useCase = AddedInLastSeenUseCaseImpl(lastSeenRepository)
    }

    private val productResults = listOf(
        ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone),
        ProductResultsFactory().create(ProductResultsFactory.ProductFactory.SecondIphone)
    )

    @Test
    fun `should add product to last seen without deleting when list is not full`() = runTest {
        //Arrange
        whenever(lastSeenRepository.getLastSeen())
            .thenReturn(productResults)
        whenever(lastSeenRepository.addedInLastSeen(any()))
            .thenReturn(Unit)

        //Act
        useCase.invoke(params = AddedInLastSeenUseCase.Params(productResults[0])).first()

        //Assert
        verify(lastSeenRepository, times(1)).getLastSeen()
        verify(lastSeenRepository, times(1)).addedInLastSeen(any())
        verify(lastSeenRepository, times(0)).deleteLastItem()
    }

    @Test
    fun `should delete last item when last seen list is full before adding new product`() = runTest {
        //Arrange
        whenever(lastSeenRepository.getLastSeen())
            .thenReturn(productResults + productResults)
        whenever(lastSeenRepository.deleteLastItem())
            .thenReturn(Unit)
        whenever(lastSeenRepository.addedInLastSeen(any()))
            .thenReturn(Unit)

        //Act
        useCase.invoke(params = AddedInLastSeenUseCase.Params(productResults[0])).first()

        //Assert
        verify(lastSeenRepository, times(1)).getLastSeen()
        verify(lastSeenRepository, times(1)).addedInLastSeen(any())
        verify(lastSeenRepository, times(1)).deleteLastItem()
    }
}