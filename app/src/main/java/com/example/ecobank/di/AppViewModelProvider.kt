package com.example.ecobank.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ecobank.EcoBankApp
import com.example.ecobank.feature.auth.AuthViewModel
import com.example.ecobank.feature.dashboard.DashboardViewModel
import com.example.ecobank.feature.deposit.DepositViewModel
import com.example.ecobank.feature.transaction.TransactionViewModel

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            AuthViewModel(
                authRepository = ecoBankApplication().container.authRepository,
            )
        }
        initializer {
            DashboardViewModel(
                profileRepository = ecoBankApplication().container.profileRepository,
            )
        }
        initializer {
            DepositViewModel(
                wasteRepository = ecoBankApplication().container.wasteRepository,
            )
        }
        initializer {
            TransactionViewModel(
                transactionRepository = ecoBankApplication().container.transactionRepository,
            )
        }
    }
}

fun CreationExtras.ecoBankApplication(): EcoBankApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EcoBankApp)
