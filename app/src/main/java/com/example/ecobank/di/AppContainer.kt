package com.example.ecobank.di

import com.example.ecobank.data.remote.SupabaseClientProvider
import com.example.ecobank.data.repository.AuthRepository
import com.example.ecobank.data.repository.AuthRepositoryImpl
import com.example.ecobank.data.repository.ProfileRepository
import com.example.ecobank.data.repository.ProfileRepositoryImpl
import com.example.ecobank.data.repository.TransactionRepository
import com.example.ecobank.data.repository.TransactionRepositoryImpl
import com.example.ecobank.data.repository.WasteRepository
import com.example.ecobank.data.repository.WasteRepositoryImpl
import io.github.jan.supabase.SupabaseClient

/**
 * Dependency Injection Container for managing app-wide singletons and repositories.
 */
interface AppContainer {
    val supabaseClient: SupabaseClient
    val authRepository: AuthRepository
    val profileRepository: ProfileRepository
    val wasteRepository: WasteRepository
    val transactionRepository: TransactionRepository
}

class DefaultAppContainer : AppContainer {
    override val supabaseClient: SupabaseClient by lazy {
        SupabaseClientProvider.client
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(supabaseClient)
    }

    override val profileRepository: ProfileRepository by lazy {
        ProfileRepositoryImpl(supabaseClient)
    }

    override val wasteRepository: WasteRepository by lazy {
        WasteRepositoryImpl(supabaseClient)
    }

    override val transactionRepository: TransactionRepository by lazy {
        TransactionRepositoryImpl(supabaseClient)
    }
}
