package com.example.ecobank.data.repository

import com.example.ecobank.data.model.Profile

/**
 * Repository interface for User Profile operations.
 */
interface ProfileRepository {
    suspend fun getProfile(userId: String): Result<Profile>
    suspend fun updateProfile(profile: Profile): Result<Profile>
}
