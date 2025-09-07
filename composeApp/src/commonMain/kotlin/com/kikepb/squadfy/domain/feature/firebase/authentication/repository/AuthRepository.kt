package com.kikepb.squadfy.domain.feature.firebase.authentication.repository

import com.kikepb.squadfy.domain.common.Either
import com.kikepb.squadfy.domain.common.FailureModel
import com.kikepb.squadfy.domain.feature.user.model.UserModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.model.AuthUserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthUserModel?>
    suspend fun loginWithEmailAndPassword(email: String, password: String): Either<FailureModel, AuthUserModel>
    suspend fun registerWithEmailAndPassword(email: String, password: String, userModel: UserModel): Either<FailureModel, String>
    suspend fun logout()
}