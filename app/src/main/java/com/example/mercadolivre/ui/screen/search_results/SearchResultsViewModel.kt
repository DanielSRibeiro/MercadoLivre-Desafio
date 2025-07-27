package com.example.mercadolivre.ui.screen.search_results

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.SearchRemoteUseCase
import com.example.core.domain.usecase.base.ResultStatus
import com.example.mercadolivre.util.Constants.SEARCH_RESULT_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    private val searchRemoteUseCase: SearchRemoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val query = savedStateHandle.get<String>(SEARCH_RESULT_ARGUMENT_KEY)

    init {
        query?.let {
            viewModelScope.launch {
                searchRemoteUseCase.invoke(
                    SearchRemoteUseCase.Params(query)
                ).collectLatest {
                    when(it) {
                        is ResultStatus.Failure -> {
                            Log.d("FUUUUUUUUNCIONEEEEE", "Erro: ${it.status.name} - ${it.message}")
                        }
                        ResultStatus.Loading -> {
                            Log.d("FUUUUUUUUNCIONEEEEE", "Loading")
                        }
                        is ResultStatus.Success -> {
                            Log.d("FUUUUUUUUNCIONEEEEE", "Sucesso: ${it.data}")
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}