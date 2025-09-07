package com.kikepb.squadfy.domain.feature.club.model

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class ClubModel(
    val id: String? = "",
    val name: String = "",
    val description: String = "",
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val adminUserId: List<String> = emptyList(),
    val inviteCode: String = "",
    val logoUrl: String? = null,
    val memberPlayersIds: List<String> = emptyList(),
    val location: String? = null
)