package com.kikepb.squadfy.domain.common

sealed class FailureModel {
    data class AuthError(val code: AuthErrorCode, val message: String) : FailureModel()
    data class NetworkError(val message: String) : FailureModel()
    data class GenericError(val message: String) : FailureModel()
}

enum class AuthErrorCode {
    INVALID_EMAIL,
    EMAIL_ALREADY_IN_USE,
    WEAK_PASSWORD,
    UNKNOWN_AUTH_ERROR
}