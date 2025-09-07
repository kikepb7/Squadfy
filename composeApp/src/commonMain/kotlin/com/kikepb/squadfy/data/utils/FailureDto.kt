package com.kikepb.squadfy.data.utils

//import com.google.firebase.FirebaseNetworkException
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
//import com.google.firebase.auth.FirebaseAuthUserCollisionException
//import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.kikepb.squadfy.domain.common.AuthErrorCode
import com.kikepb.squadfy.domain.common.FailureModel

data class FailureDto(val code: Int, val message: String?)

enum class AuthErrorCodeDto(val code: Int) {
    INVALID_EMAIL(1001),
    EMAIL_ALREADY_IN_USE(1002),
    WEAK_PASSWORD(1003),
    NETWORK(2001),
    UNKNOWN(9999)
}

fun Exception.toFailureDto(): FailureDto {
    return when (this) {
        else -> FailureDto(AuthErrorCodeDto.UNKNOWN.code, this.message ?: "Error desconocido")
    }
}

fun FailureDto.toFailureModel(): FailureModel {
    return when (this.code) {
        AuthErrorCodeDto.INVALID_EMAIL.code -> FailureModel.AuthError(AuthErrorCode.INVALID_EMAIL, this.message ?: "")
        AuthErrorCodeDto.EMAIL_ALREADY_IN_USE.code -> FailureModel.AuthError(AuthErrorCode.EMAIL_ALREADY_IN_USE, this.message ?: "")
        AuthErrorCodeDto.WEAK_PASSWORD.code -> FailureModel.AuthError(AuthErrorCode.WEAK_PASSWORD, this.message ?: "")
        AuthErrorCodeDto.NETWORK.code -> FailureModel.NetworkError(this.message ?: "")
        else -> FailureModel.GenericError(this.message ?: "Error desconocido")
    }
}