package com.kikepb.squadfy.domain.feature.user.repository

import com.kikepb.squadfy.domain.feature.user.model.UserModel

interface UserRepository {
    suspend fun createUser(user: UserModel): String
}