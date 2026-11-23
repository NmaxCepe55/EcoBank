package com.example.ecobank.data.repository

import com.example.ecobank.data.model.Profile
import com.example.ecobank.data.remote.SupabaseClientProvider
import io.github.jan.supabase.SupabaseClient

class AuthRepositoryImpl(
    private val client: SupabaseClient = SupabaseClientProvider.client,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Profile> = runCatching {
        // TODO: Implement Supabase Auth sign-in logic
        throw NotImplementedError("Login is not implemented yet")
    }

    override suspend fun register(
        fullName: String,
        email: String,
        password: String,
        phone: String?,
    ): Result<Profile> = runCatching {
        // TODO: Implement Supabase Auth sign-up logic
        throw NotImplementedError("Register is not implemented yet")
    }

    override suspend fun logout(): Result<Unit> = runCatching {
        // TODO: Implement Supabase Auth sign-out logic
    }

    override suspend fun getCurrentUser(): Result<Profile?> = runCatching {
        // TODO: Implement fetching current user profile logic
        null
    }
}
