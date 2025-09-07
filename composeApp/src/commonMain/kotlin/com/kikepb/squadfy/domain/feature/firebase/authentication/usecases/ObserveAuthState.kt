package com.kikepb.squadfy.domain.feature.firebase.authentication.usecases

import com.kikepb.squadfy.domain.feature.firebase.authentication.repository.AuthRepository

class ObserveAuthState(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.authState
}