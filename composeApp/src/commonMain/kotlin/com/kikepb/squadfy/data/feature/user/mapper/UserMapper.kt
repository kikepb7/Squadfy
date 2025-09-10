package com.kikepb.squadfy.data.feature.user.mapper

import com.kikepb.squadfy.data.feature.user.dto.UserDto
import com.kikepb.squadfy.domain.feature.user.model.UserModel

fun UserDto.toUserModel(): UserModel =
    UserModel(
        id = id,
        name = name,
        lastName = lastName,
        email = email,
        password = password,
        phoneNumber = phoneNumber,
        imageUrl = imageUrl,
        clubId = clubId,
        role = role,
        position = position,
        number = number,
        rating = rating,
        birthDate = birthDate,
        createdAt = createdAt,
        isActive = isActive
    )

fun UserModel.toUserDto(): UserDto =
    UserDto(
        id = id,
        name = name,
        lastName = lastName,
        email = email,
        password = password,
        phoneNumber = phoneNumber,
        imageUrl = imageUrl,
        clubId = clubId,
        role = role,
        position = position,
        number = number,
        rating = rating,
        birthDate = birthDate,
        createdAt = createdAt,
        isActive = isActive
    )