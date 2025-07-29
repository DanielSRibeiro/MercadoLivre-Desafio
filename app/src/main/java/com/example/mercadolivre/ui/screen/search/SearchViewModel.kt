package com.example.mercadolivre.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.local.PreferenceDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val preference: PreferenceDataSource,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState: StateFlow<SearchState> = _uiState

    init {
        _uiState.value =
            _uiState.value.copy(researchHistory = preference.getStringList())
    }

    fun event(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun saveQuery() {
        viewModelScope.launch {
            val query = _uiState.value.query
            if(query.isNotBlank()){
                val newList = listOf(query) + _uiState.value.researchHistory
                _uiState.value = _uiState.value.copy(researchHistory = newList)
                preference.saveStringList(list = newList)
            }
        }
    }
}
