package com.kikepb.squadfy.data.feature.user.dto

data class UserDto(
    val id: String? = "",
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val imageUrl: String? = "",
    val clubId: String? = null,
    val role: String = "player",
    val position: String = "",
    val number: Int? = null,
    val rating: Double = 0.0,
    val birthDate: Long? = null,
    val createdAt: Long? = null,
    val isActive: Boolean = true
)