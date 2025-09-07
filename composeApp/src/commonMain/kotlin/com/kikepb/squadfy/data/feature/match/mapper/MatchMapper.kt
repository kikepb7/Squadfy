package com.kikepb.squadfy.data.feature.match.mapper

import com.kikepb.squadfy.data.feature.match.dto.MatchDto
import com.kikepb.squadfy.domain.feature.match.model.MatchModel

fun MatchDto.toMatchModel(): MatchModel =
    MatchModel(
        id = id,
        number = number,
        date = date,
        whiteTeam = whiteTeam,
        blueTeam = blueTeam,
        whiteTeamGoals = whiteTeamGoals,
        blueTeamGoals = blueTeamGoals,
        joinedPlayers = joinedPlayers,
        mvpId = mvpId,
        isFinalized = isFinalized,
        clubId = clubId,
        createdByUserId = createdByUserId,
        location = location,
        notes = notes
    )

fun MatchModel.toMatchDto(): MatchDto =
    MatchDto(
        id = id,
        number = number,
        date = date,
        whiteTeam = whiteTeam,
        blueTeam = blueTeam,
        whiteTeamGoals = whiteTeamGoals,
        blueTeamGoals = blueTeamGoals,
        joinedPlayers = joinedPlayers,
        mvpId = mvpId,
        isFinalized = isFinalized,
        clubId = clubId,
        createdByUserId = createdByUserId,
        location = location,
        notes = notes
    )

//fun MatchDto.withIdFrom(snapshot: DocumentSnapshot): MatchDto {
//    return this.copy(id = snapshot.id)
//}