package com.example.ecobank.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WasteCategory(
    val id: String = "",
    val name: String = "",
    @SerialName("price_per_kg")
    val pricePerKg: Double = 0.0,
    @SerialName("is_active")
    val isActive: Boolean = true,
)
