package com.kikepb.squadfy.data.feature.statistics.dto

data class PlayerMatchStatsDto(
    val id: String? = "",
    val playerId: String,
    val matchId: String,
    val clubId: String? = null,
    val goals: Int = 0,
    val assists: Int = 0,
    val saves: Int = 0,
    val yellowCards: Int = 0,
    val redCards: Int = 0,
    val isMvp: Boolean = false,
    val ratingBefore: Double = 0.0,
    val ratingAfter: Double = 0.0,
    val minutesPlayed: Int = 0,
    val createdAt: Long? = null
)