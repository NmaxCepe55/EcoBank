package com.example.ecobank.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String = "",
    @SerialName("full_name")
    val fullName: String = "",
    val email: String = "",
    val phone: String? = null,
    val role: String = UserRole.NASABAH.value,
    @SerialName("created_at")
    val createdAt: String? = null,
) {
    val userRole: UserRole
        get() = UserRole.fromString(role)
}
