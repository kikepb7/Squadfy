package com.kikepb.squadfy.data.feature.player.dto

data class PlayerDto(
    val id: String? = "",
    val name: String = "",
    val number: Int = 0,
    val position: String = "",
    val imageUrl: String? = null,
    val userId: String? = null,
    val clubId: String? = null,
    val rating: Double = 0.0,
    val isActive: Boolean = true,
    val birthDate: Long? = null,
    val joinedAt: Long? = null
)