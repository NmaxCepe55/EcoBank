package com.example.ecobank.data.repository

import com.example.ecobank.data.model.WasteCategory
import com.example.ecobank.data.model.WasteDeposit

/**
 * Repository interface for Waste Categories & Waste Deposits operations.
 */
interface WasteRepository {
    suspend fun getActiveCategories(): Result<List<WasteCategory>>
    suspend fun createDeposit(deposit: WasteDeposit): Result<WasteDeposit>
    suspend fun getUserDeposits(userId: String): Result<List<WasteDeposit>>
    suspend fun getAllDeposits(): Result<List<WasteDeposit>>
    suspend fun updateDepositStatus(depositId: String, status: String, notes: String?): Result<Unit>
}
