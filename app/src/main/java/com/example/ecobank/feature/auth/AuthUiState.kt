package com.example.ecobank.feature.auth

import com.example.ecobank.data.model.Profile
import com.example.ecobank.ui.common.UiState

data class AuthUiState(
    val authState: UiState<Profile> = UiState.Idle,
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val phone: String = "",
)
