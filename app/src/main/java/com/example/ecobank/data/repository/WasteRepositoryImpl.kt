package com.example.ecobank.data.repository

import com.example.ecobank.data.model.WasteCategory
import com.example.ecobank.data.model.WasteDeposit
import com.example.ecobank.data.remote.SupabaseClientProvider
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class WasteRepositoryImpl(
    private val client: SupabaseClient = SupabaseClientProvider.client,
) : WasteRepository {

    override suspend fun getActiveCategories(): Result<List<WasteCategory>> = runCatching {
        client.postgrest["waste_categories"]
            .select {
                filter {
                    eq("is_active", true)
                }
            }
            .decodeList<WasteCategory>()
    }

    override suspend fun createDeposit(deposit: WasteDeposit): Result<WasteDeposit> = runCatching {
        client.postgrest["waste_deposits"]
            .insert(deposit) {
                select()
            }
            .decodeSingle<WasteDeposit>()
    }

    override suspend fun getUserDeposits(userId: String): Result<List<WasteDeposit>> = runCatching {
        client.postgrest["waste_deposits"]
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeList<WasteDeposit>()
    }

    override suspend fun getAllDeposits(): Result<List<WasteDeposit>> = runCatching {
        client.postgrest["waste_deposits"]
            .select()
            .decodeList<WasteDeposit>()
    }

    override suspend fun updateDepositStatus(
        depositId: String,
        status: String,
        notes: String?,
    ): Result<Unit> = runCatching {
        client.postgrest["waste_deposits"]
            .update({
                set("status", status)
                notes?.let { set("notes", it) }
            }) {
                filter {
                    eq("id", depositId)
                }
            }
    }
}
