package com.example.ecobank.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WasteDeposit(
    val id: String = "",
    @SerialName("user_id")
    val userId: String = "",
    @SerialName("category_id")
    val categoryId: String = "",
    val weight: Double = 0.0,
    @SerialName("price_per_kg")
    val pricePerKg: Double = 0.0,
    @SerialName("total_price")
    val totalPrice: Double = 0.0,
    @SerialName("image_url")
    val imageUrl: String = "",
    val status: String = DepositStatus.PENDING.value,
    val notes: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
) {
    val depositStatus: DepositStatus
        get() = DepositStatus.fromString(status)
}
