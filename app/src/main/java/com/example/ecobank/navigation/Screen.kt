package com.example.ecobank.navigation

/**
 * Route definitions for EcoBank app navigation.
 */
sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Dashboard : Screen("dashboard")
    data object Deposit : Screen("deposit")
    data object Transaction : Screen("transaction")
    data object TransactionDetail : Screen("transaction_detail/{depositId}") {
        fun createRoute(depositId: String) = "transaction_detail/$depositId"
    }
    data object Profile : Screen("profile")
    data object AdminDashboard : Screen("admin_dashboard")
    data object AdminVerify : Screen("admin_verify/{depositId}") {
        fun createRoute(depositId: String) = "admin_verify/$depositId"
    }
}
