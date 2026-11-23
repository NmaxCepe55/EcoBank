package com.example.ecobank.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecobank.di.AppViewModelProvider
import com.example.ecobank.feature.auth.AuthViewModel
import com.example.ecobank.feature.auth.LoginScreen
import com.example.ecobank.feature.auth.RegisterScreen
import com.example.ecobank.feature.dashboard.AdminDashboardScreen
import com.example.ecobank.feature.dashboard.AdminVerifyScreen
import com.example.ecobank.feature.dashboard.DashboardScreen
import com.example.ecobank.feature.dashboard.DashboardViewModel
import com.example.ecobank.feature.dashboard.ProfileScreen
import com.example.ecobank.feature.deposit.DepositScreen
import com.example.ecobank.feature.deposit.DepositViewModel
import com.example.ecobank.feature.transaction.TransactionDetailScreen
import com.example.ecobank.feature.transaction.TransactionScreen
import com.example.ecobank.feature.transaction.TransactionViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        // Login Screen
        composable(Screen.Login.route) {
            val authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
            )
        }

        // Register Screen
        composable(Screen.Register.route) {
            val authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigateUp()
                },
            )
        }

        // Dashboard Nasabah
        composable(Screen.Dashboard.route) {
            val dashboardViewModel: DashboardViewModel = viewModel(factory = AppViewModelProvider.Factory)
            DashboardScreen(
                onNavigateToDeposit = {
                    navController.navigate(Screen.Deposit.route)
                },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transaction.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToAdminDashboard = {
                    navController.navigate(Screen.AdminDashboard.route)
                },
            )
        }

        // Setor Sampah (Deposit)
        composable(Screen.Deposit.route) {
            val depositViewModel: DepositViewModel = viewModel(factory = AppViewModelProvider.Factory)
            DepositScreen(
                onDepositSubmitted = {
                    navController.navigateUp()
                },
            )
        }

        // Riwayat Transaksi
        composable(Screen.Transaction.route) {
            val transactionViewModel: TransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)
            TransactionScreen(
                onNavigateToDetail = { depositId ->
                    navController.navigate(Screen.TransactionDetail.createRoute(depositId))
                },
            )
        }

        // Detail Transaksi
        composable(
            route = Screen.TransactionDetail.route,
            arguments = listOf(navArgument("depositId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val depositId = backStackEntry.arguments?.getString("depositId") ?: ""
            TransactionDetailScreen(
                depositId = depositId,
                onNavigateBack = {
                    navController.navigateUp()
                },
            )
        }

        // Profil User
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
            )
        }

        // Dashboard Admin
        composable(Screen.AdminDashboard.route) {
            AdminDashboardScreen(
                onNavigateToVerify = { depositId ->
                    navController.navigate(Screen.AdminVerify.createRoute(depositId))
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
            )
        }

        // Admin Verifikasi Setoran
        composable(
            route = Screen.AdminVerify.route,
            arguments = listOf(navArgument("depositId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val depositId = backStackEntry.arguments?.getString("depositId") ?: ""
            AdminVerifyScreen(
                depositId = depositId,
                onNavigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}
