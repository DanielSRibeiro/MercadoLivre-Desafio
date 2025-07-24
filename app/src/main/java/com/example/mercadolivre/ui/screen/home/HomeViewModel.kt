package com.example.mercadolivre.ui.screen.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetAccessTokenUseCase
import com.example.core.domain.usecase.GetTokenUseCase
import com.example.core.domain.usecase.SaveTokenUseCase
import com.example.core.domain.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            val timeNow = Instant.now()
            val accessToken = getTokenUseCase.invoke()

            val tokenIsExpired = accessToken?.expiresAt?.let { it < timeNow.epochSecond } ?: true
            if (tokenIsExpired) getAccessToken()
        }
    }

    private suspend fun getAccessToken() {
        getAccessTokenUseCase.invoke(
            params = GetAccessTokenUseCase.Params(Unit)
        ).collectLatest { result ->
            when (result) {
                ResultStatus.Loading -> {
                    Log.d("FUNCIONOUUUUUUUUU", "Loading")
                }

                is ResultStatus.Success -> {
                    saveTokenUseCase.invoke(
                        params = SaveTokenUseCase.Params(token = result.data)
                    )
                    Log.d("FUNCIONOUUUUUUUUU", "Sucesso: ${result.data}")
                }

                is ResultStatus.Failure -> {
                    Log.d("FUNCIONOUUUUUUUUU", "Falha: ${result.status}")
                }
            }
        }
    }
}
