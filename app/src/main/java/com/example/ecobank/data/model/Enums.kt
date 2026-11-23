package com.example.ecobank.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    @SerialName("nasabah")
    NASABAH,

    @SerialName("admin")
    ADMIN;

    val value: String
        get() = when (this) {
            NASABAH -> "nasabah"
            ADMIN -> "admin"
        }

    companion object {
        fun fromString(role: String?): UserRole {
            return entries.find { it.value.equals(role, ignoreCase = true) } ?: NASABAH
        }
    }
}

@Serializable
enum class DepositStatus {
    @SerialName("pending")
    PENDING,

    @SerialName("approved")
    APPROVED,

    @SerialName("rejected")
    REJECTED;

    val value: String
        get() = when (this) {
            PENDING -> "pending"
            APPROVED -> "approved"
            REJECTED -> "rejected"
        }

    companion object {
        fun fromString(status: String?): DepositStatus {
            return entries.find { it.value.equals(status, ignoreCase = true) } ?: PENDING
        }
    }
}

@Serializable
enum class TransactionType {
    @SerialName("deposit_credit")
    DEPOSIT_CREDIT;

    val value: String
        get() = when (this) {
            DEPOSIT_CREDIT -> "deposit_credit"
        }

    companion object {
        fun fromString(type: String?): TransactionType {
            return entries.find { it.value.equals(type, ignoreCase = true) } ?: DEPOSIT_CREDIT
        }
    }
}
