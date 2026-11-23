package com.example.ecobank.data.repository

import com.example.ecobank.data.model.Transaction
import com.example.ecobank.data.remote.SupabaseClientProvider
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class TransactionRepositoryImpl(
    private val client: SupabaseClient = SupabaseClientProvider.client,
) : TransactionRepository {

    override suspend fun getUserTransactions(userId: String): Result<List<Transaction>> = runCatching {
        client.postgrest["transactions"]
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeList<Transaction>()
    }

    override suspend fun getAllTransactions(): Result<List<Transaction>> = runCatching {
        client.postgrest["transactions"]
            .select()
            .decodeList<Transaction>()
    }

    override suspend fun createTransaction(transaction: Transaction): Result<Transaction> = runCatching {
        client.postgrest["transactions"]
            .insert(transaction) {
                select()
            }
            .decodeSingle<Transaction>()
    }
}
