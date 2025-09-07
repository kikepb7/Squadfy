package com.kikepb.squadfy.ui.common.extensions

import com.kikepb.squadfy.domain.common.AuthErrorCode
import com.kikepb.squadfy.domain.common.FailureModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import squadfy.composeapp.generated.resources.Res
import squadfy.composeapp.generated.resources.ic_red_card_error
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun Long.toFormattedDate(): String {
    val instant = Instant.fromEpochMilliseconds(this)
    val date = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    return "${date.day.toString().padStart(2, '0')}/" +
            "${date.month.number.toString().padStart(2, '0')}/" +
            "${date.year}"
}

@OptIn(ExperimentalTime::class)
fun getCurrentMonday(): LocalDate {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    return today.minus(today.dayOfWeek.ordinal, DateTimeUnit.DAY)
}

@OptIn(ExperimentalTime::class)
fun Long.toLocalDate(): LocalDate =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.currentSystemDefault()).date

@OptIn(ExperimentalTime::class)
fun LocalDate.toMillis(): Long =
    this.atStartOfDayIn(TimeZone.currentSystemDefault()).toEpochMilliseconds()

data class UiError(
    val title: String = "Error",
    val message: String,
    val image: DrawableResource = Res.drawable.ic_red_card_error,
    val buttonText: String = "Aceptar"
)

fun FailureModel.toUiError(): UiError {
    return when (this) {
        is FailureModel.AuthError -> when (this.code) {
            AuthErrorCode.INVALID_EMAIL -> UiError(message = "El correo electrónico o la contraseña no son válidos")
            AuthErrorCode.EMAIL_ALREADY_IN_USE -> UiError(message = "Este correo ya está registrado")
            AuthErrorCode.WEAK_PASSWORD -> UiError(message = "La contraseña es demasiado débil")
            AuthErrorCode.UNKNOWN_AUTH_ERROR -> UiError(message = "Error desconocido de autenticación")
        }

        is FailureModel.NetworkError -> UiError(message = "No tienes conexión a internet")
        is FailureModel.GenericError -> UiError(message = message.ifBlank { "Ha ocurrido un error inesperado" })
    }
}

fun Throwable.toUiFailure(): FailureModel {
    return when (this) {
        is CancellationException -> throw this

        else -> {
            FailureModel.GenericError(this.message ?: "Ha ocurrido un error inesperado")
        }
    }
}