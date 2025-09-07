package com.kikepb.squadfy.data.feature.player.mapper

import com.kikepb.squadfy.data.feature.player.dto.PlayerDto
import com.kikepb.squadfy.domain.feature.player.model.PlayerModel

fun PlayerDto.toPlayerModel(): PlayerModel =
    PlayerModel(
        id = id,
        name = name,
        number = number,
        position = position,
        imageUrl = imageUrl,
        userId = userId,
        clubId = clubId,
        isActive = isActive,
        birthDate = birthDate,
        joinedAt = joinedAt
    )

fun PlayerModel.toPlayerDto(): PlayerDto = PlayerDto(
    id = id,
    name = name,
    number = number,
    position = position,
    imageUrl = imageUrl,
    userId = userId,
    clubId = clubId,
    isActive = isActive,
    birthDate = birthDate,
    joinedAt = joinedAt
)