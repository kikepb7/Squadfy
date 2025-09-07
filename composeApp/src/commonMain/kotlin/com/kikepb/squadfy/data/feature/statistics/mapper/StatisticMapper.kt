package com.kikepb.squadfy.data.feature.statistics.mapper

import com.kikepb.squadfy.data.feature.statistics.dto.PlayerMatchStatsDto
import com.kikepb.squadfy.domain.feature.statistics.model.PlayerStatisticModel

fun PlayerMatchStatsDto.toStatisticModel(): PlayerStatisticModel =
    PlayerStatisticModel(
        id = id,
        playerId = playerId,
        matchId = matchId,
        clubId = clubId,
        goals = goals,
        assists = assists,
        saves = saves,
        yellowCards = yellowCards,
        redCards = redCards,
        isMvp = isMvp,
        ratingBefore = ratingBefore,
        ratingAfter = ratingAfter,
        minutesPlayed = minutesPlayed,
        createdAt = createdAt
    )

fun PlayerStatisticModel.toStatisticDto(): PlayerMatchStatsDto =
    PlayerMatchStatsDto(
        id = id,
        playerId = playerId,
        matchId = matchId,
        clubId = clubId,
        goals = goals,
        assists = assists,
        saves = saves,
        yellowCards = yellowCards,
        redCards = redCards,
        isMvp = isMvp,
        ratingBefore = ratingBefore,
        ratingAfter = ratingAfter,
        minutesPlayed = minutesPlayed,
        createdAt = createdAt
    )