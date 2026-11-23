package com.example.ecobank.feature.deposit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecobank.data.model.WasteCategory
import com.example.ecobank.data.repository.WasteRepository
import com.example.ecobank.data.repository.WasteRepositoryImpl
import com.example.ecobank.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DepositViewModel(
    private val wasteRepository: WasteRepository = WasteRepositoryImpl(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(DepositUiState())
    val uiState: StateFlow<DepositUiState> = _uiState.asStateFlow()

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            _uiState.update { it.copy(categoriesState = UiState.Loading) }
            wasteRepository.getActiveCategories()
                .onSuccess { categories ->
                    _uiState.update { it.copy(categoriesState = UiState.Success(categories)) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(categoriesState = UiState.Error(error.message ?: "Gagal memuat kategori"))
                    }
                }
        }
    }

    fun selectCategory(category: WasteCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun updateWeight(weight: String) {
        _uiState.update { it.copy(weightKg = weight) }
    }
}
