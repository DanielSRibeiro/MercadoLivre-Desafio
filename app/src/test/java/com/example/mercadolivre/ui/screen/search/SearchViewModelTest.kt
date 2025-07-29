package com.example.mercadolivre.ui.screen.search

import com.example.core.data.local.PreferenceDataSource
import com.example.testing.MainCoroutineRule
import com.example.testing.model.ResearchHistoryFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var preference: PreferenceDataSource

    private val viewModel by lazy { SearchViewModel(preference = preference) }

    private val researchHistoryFake = ResearchHistoryFactory().create()

    @Test
    fun `should initialize with history from preferences`() = runTest {
        // Arrange
        whenever(preference.getStringList())
            .thenReturn(researchHistoryFake)

        // Act
        viewModel

        // Assert
        viewModel.uiState.value.researchHistory.map { string ->
            assertTrue(researchHistoryFake.contains(string))
        }
    }

    @Test
    fun `should save query to preferences and update state`() = runTest {
        // Arrange
        whenever(preference.getStringList())
            .thenReturn(researchHistoryFake)
        val query = "ps5"

        // Act
        viewModel.event(query)
        viewModel.saveQuery()

        // Assert
        val expectedList = listOf(query) + researchHistoryFake
        val updatedHistory = viewModel.uiState.value.researchHistory
        assertEquals(expectedList, updatedHistory)
        verify(preference).saveStringList(expectedList)
    }
}