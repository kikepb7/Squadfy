package com.kikepb.squadfy.domain.feature.firebase.authentication.usecases

import com.kikepb.squadfy.domain.feature.firebase.authentication.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke() = authRepository.logout()
}