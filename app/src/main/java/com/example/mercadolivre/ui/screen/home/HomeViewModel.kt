package com.example.mercadolivre.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.ErrorStates
import com.example.core.domain.model.Product
import com.example.core.domain.usecase.GetAccessTokenUseCase
import com.example.core.domain.usecase.GetAllFavoritesUseCase
import com.example.core.domain.usecase.GetLastSeenUseCase
import com.example.core.domain.usecase.GetTokenUseCase
import com.example.core.domain.usecase.SaveTokenUseCase
import com.example.core.domain.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getLastSeenUseCase: GetLastSeenUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    init {
        viewModelScope.launch {
            val timeNow = Instant.now()
            val accessToken = getTokenUseCase.invoke()

            val tokenIsExpired = accessToken?.expiresAt?.let { it < timeNow.epochSecond } ?: true
            if (tokenIsExpired) getAccessToken()

            getLastSeenUseCase.invoke(
                params = GetLastSeenUseCase.Params(Unit)
            ).collectLatest {
                when (it) {
                    is ResultStatus.Success ->
                        _uiState.value = _uiState.value.copy(lastSeenProducts = it.data)
                    else -> {}
                }
            }

            getAllFavoritesUseCase.invoke(
                params = GetAllFavoritesUseCase.Params(Unit)
            ).collectLatest {
                when (it) {
                    is ResultStatus.Success ->
                        _uiState.value = _uiState.value.copy(favoritesProducts = it.data)
                    else -> {}
                }
            }
        }
    }

    private suspend fun getAccessToken() {
        getAccessTokenUseCase.invoke(
            params = GetAccessTokenUseCase.Params(Unit)
        ).collectLatest { result ->
            when (result) {
                ResultStatus.Loading -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        isSuccess = false,
                        isNetworkError = false,
                        isError = false
                    )
                }

                is ResultStatus.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        isNetworkError = false,
                        isError = false
                    )
                    saveTokenUseCase.invoke(
                        params = SaveTokenUseCase.Params(token = result.data)
                    )
                }

                is ResultStatus.Failure -> {
                    val isErrorNetwork = result.status == ErrorStates.NoInternet
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = false,
                        isNetworkError = isErrorNetwork,
                        isError = !isErrorNetwork
                    )
                }
            }
        }
    }
}

data class HomeState(
    val lastSeenProducts: List<Product> = emptyList(),
    val favoritesProducts: List<Product> = emptyList(),
    val isNetworkError: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
)