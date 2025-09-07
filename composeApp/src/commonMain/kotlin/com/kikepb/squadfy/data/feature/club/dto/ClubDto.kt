package com.kikepb.squadfy.data.feature.club.dto

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class ClubDto @OptIn(ExperimentalTime::class) constructor(
    override var id: String? = "",
    val name: String = "",
    val description: String = "",
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val adminUserId: List<String> = emptyList(),
    val inviteCode: String = "",
    val logoUrl: String? = null,
    val memberPlayersIds: List<String> = emptyList(),
    val location: String? = null
) : FirestoreIdentifiable

interface FirestoreIdentifiable {
    var id: String?
}