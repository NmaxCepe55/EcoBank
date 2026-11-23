package com.example.ecobank.feature.deposit

import com.example.ecobank.data.model.WasteCategory
import com.example.ecobank.data.model.WasteDeposit
import com.example.ecobank.ui.common.UiState

data class DepositUiState(
    val categoriesState: UiState<List<WasteCategory>> = UiState.Idle,
    val submitState: UiState<WasteDeposit> = UiState.Idle,
    val selectedCategory: WasteCategory? = null,
    val weightKg: String = "",
    val imageUrl: String = "",
    val notes: String = "",
)
