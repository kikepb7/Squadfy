package com.kikepb.squadfy.core.error

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.kikepb.squadfy.domain.common.AuthErrorCode.EMAIL_ALREADY_IN_USE
import com.kikepb.squadfy.domain.common.AuthErrorCode.INVALID_EMAIL
import com.kikepb.squadfy.domain.common.AuthErrorCode.UNKNOWN_AUTH_ERROR
import com.kikepb.squadfy.domain.common.AuthErrorCode.WEAK_PASSWORD
import com.kikepb.squadfy.domain.common.FailureModel
import kotlinx.io.IOException

actual fun Throwable.toFailureModel(): FailureModel {
    return when (this) {
        is FirebaseAuthInvalidCredentialsException -> FailureModel.AuthError(code = INVALID_EMAIL, message = message ?: "")
        is FirebaseAuthUserCollisionException -> FailureModel.AuthError(code = EMAIL_ALREADY_IN_USE, message = message ?: "")
        is FirebaseAuthWeakPasswordException -> FailureModel.AuthError(code = WEAK_PASSWORD, message = message ?: "")
        is IOException -> FailureModel.NetworkError(message = message ?: "Error de red")
        else -> FailureModel.AuthError(code = UNKNOWN_AUTH_ERROR, message = message ?: "Error desconocido")
    }
}