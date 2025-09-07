package com.kikepb.squadfy.domain.feature.firebase.authentication.usecases

import com.kikepb.squadfy.domain.common.Either
import com.kikepb.squadfy.domain.common.FailureModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.model.AuthUserModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.repository.AuthRepository

class LoginWithEmailPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Either<FailureModel, AuthUserModel> {
        return authRepository.loginWithEmailAndPassword(email = email, password = password)
    }
}