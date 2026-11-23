package com.example.ecobank.data.repository

import com.example.ecobank.data.model.Profile
import com.example.ecobank.data.remote.SupabaseClientProvider
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class ProfileRepositoryImpl(
    private val client: SupabaseClient = SupabaseClientProvider.client,
) : ProfileRepository {

    override suspend fun getProfile(userId: String): Result<Profile> = runCatching {
        client.postgrest["profiles"]
            .select {
                filter {
                    eq("id", userId)
                }
            }
            .decodeSingle<Profile>()
    }

    override suspend fun updateProfile(profile: Profile): Result<Profile> = runCatching {
        client.postgrest["profiles"]
            .update(profile) {
                filter {
                    eq("id", profile.id)
                }
                select()
            }
            .decodeSingle<Profile>()
    }
}
