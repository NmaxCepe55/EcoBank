package com.example.ecobank.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecobank.data.repository.ProfileRepository
import com.example.ecobank.data.repository.ProfileRepositoryImpl
import com.example.ecobank.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val profileRepository: ProfileRepository = ProfileRepositoryImpl(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun loadProfile(userId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(profileState = UiState.Loading) }
            profileRepository.getProfile(userId)
                .onSuccess { profile ->
                    _uiState.update { it.copy(profileState = UiState.Success(profile)) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(profileState = UiState.Error(error.message ?: "Gagal memuat profil"))
                    }
                }
        }
    }
}
