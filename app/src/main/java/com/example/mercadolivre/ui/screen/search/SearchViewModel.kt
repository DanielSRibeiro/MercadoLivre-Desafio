package com.example.mercadolivre.ui.screen.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolivre.util.PreferenceHelper.getStringList
import com.example.mercadolivre.util.PreferenceHelper.saveStringList
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState: StateFlow<SearchState> = _uiState

    init {
        _uiState.value =
            _uiState.value.copy(researchHistory = getStringList(context))
    }

    fun event(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun saveQuery() {
        viewModelScope.launch {
            val query = _uiState.value.query
            val newList = listOf(query) + _uiState.value.researchHistory
            _uiState.value = _uiState.value.copy(researchHistory = newList)
            saveStringList(context = context, list = newList)
        }
    }
}

data class SearchState(
    val query: String = "",
    val researchHistory: List<String> = emptyList(),
)