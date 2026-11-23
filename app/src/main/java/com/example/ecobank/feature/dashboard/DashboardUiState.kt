package com.example.ecobank.feature.dashboard

import com.example.ecobank.data.model.Profile
import com.example.ecobank.ui.common.UiState

data class DashboardUiState(
    val profileState: UiState<Profile> = UiState.Idle,
)
