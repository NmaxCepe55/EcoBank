package com.example.ecobank.data.repository

import com.example.ecobank.data.model.Profile

/**
 * Repository interface for Authentication & User session management.
 */
interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Profile>
    suspend fun register(fullName: String, email: String, password: String, phone: String?): Result<Profile>
    suspend fun logout(): Result<Unit>
    suspend fun getCurrentUser(): Result<Profile?>
}
