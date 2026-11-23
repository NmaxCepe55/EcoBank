package com.example.ecobank.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecobank.data.repository.AuthRepository
import com.example.ecobank.data.repository.AuthRepositoryImpl
import com.example.ecobank.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun login() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        viewModelScope.launch {
            _uiState.update { it.copy(authState = UiState.Loading) }
            authRepository.login(email, password)
                .onSuccess { profile ->
                    _uiState.update { it.copy(authState = UiState.Success(profile)) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(authState = UiState.Error(error.message ?: "Login gagal")) }
                }
        }
    }
}
