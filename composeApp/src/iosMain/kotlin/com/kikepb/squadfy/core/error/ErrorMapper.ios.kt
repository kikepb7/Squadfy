package com.kikepb.squadfy.core.error

import com.kikepb.squadfy.domain.common.AuthErrorCode
import com.kikepb.squadfy.domain.common.FailureModel
import platform.Foundation.NSError

actual fun Throwable.toFailureModel(): FailureModel {
    val nsError = this as NSError
    return when (this.code.toInt()) {
        17008  -> FailureModel.AuthError(code = AuthErrorCode.INVALID_EMAIL, message = nsError.localizedDescription)
        17007  -> FailureModel.AuthError(code = AuthErrorCode.EMAIL_ALREADY_IN_USE, message = nsError.localizedDescription)
        17026  -> FailureModel.AuthError(code = AuthErrorCode.WEAK_PASSWORD, message = nsError.localizedDescription)
        else -> FailureModel.AuthError(code = AuthErrorCode.UNKNOWN_AUTH_ERROR, message = nsError.localizedDescription)
    }
}