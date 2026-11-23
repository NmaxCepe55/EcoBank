package com.example.ecobank.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String = "",
    @SerialName("user_id")
    val userId: String = "",
    @SerialName("deposit_id")
    val depositId: String = "",
    val amount: Double = 0.0,
    val type: String = TransactionType.DEPOSIT_CREDIT.value,
    @SerialName("created_at")
    val createdAt: String? = null,
) {
    val transactionType: TransactionType
        get() = TransactionType.fromString(type)
}
