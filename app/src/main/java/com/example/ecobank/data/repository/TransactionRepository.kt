package com.example.ecobank.data.repository

import com.example.ecobank.data.model.Transaction

/**
 * Repository interface for Transactions operations.
 */
interface TransactionRepository {
    suspend fun getUserTransactions(userId: String): Result<List<Transaction>>
    suspend fun getAllTransactions(): Result<List<Transaction>>
    suspend fun createTransaction(transaction: Transaction): Result<Transaction>
}
