package com.example.ecobank.feature.transaction

import com.example.ecobank.data.model.Transaction
import com.example.ecobank.ui.common.UiState

data class TransactionUiState(
    val transactionsState: UiState<List<Transaction>> = UiState.Idle,
)
