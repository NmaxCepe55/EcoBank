package com.example.ecobank.feature.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecobank.data.repository.TransactionRepository
import com.example.ecobank.data.repository.TransactionRepositoryImpl
import com.example.ecobank.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionRepository: TransactionRepository = TransactionRepositoryImpl(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    fun loadUserTransactions(userId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(transactionsState = UiState.Loading) }
            transactionRepository.getUserTransactions(userId)
                .onSuccess { list ->
                    _uiState.update { it.copy(transactionsState = UiState.Success(list)) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(transactionsState = UiState.Error(error.message ?: "Gagal memuat transaksi"))
                    }
                }
        }
    }
}
