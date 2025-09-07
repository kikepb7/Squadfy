package com.kikepb.squadfy.ui.feature.home.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikepb.squadfy.domain.feature.club.model.ClubModel
import com.kikepb.squadfy.domain.feature.player.model.PlayerModel
import com.kikepb.squadfy.domain.feature.user.model.ClubMemberModel
import com.kikepb.squadfy.domain.feature.user.model.ImageMetaDataModel
import com.kikepb.squadfy.domain.feature.user.model.UserModel
import com.kikepb.squadfy.utils.getDeviceName
import com.kikepb.squadfy.utils.readImageBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class RegisterStepViewModel(
//    private val registerUserUseCase: RegisterUserUseCase,
//    private val uploadUserImageUseCase: UploadUserImageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state = _state.asStateFlow()
    private var uploadedImageUrl: String? = null

    private inline fun updateFormState(block: RegisterState.Form.() -> RegisterState.Form) {
        val current = _state.value as? RegisterState.Form ?: RegisterState.Form()
        _state.update { block(current) }
    }

    private inline fun updateUser(block: UserModel.() -> UserModel) = updateFormState {
        val member = clubMember ?: ClubMemberModel(user = UserModel())
        copy(clubMember = member.copy(user = member.user.block()))
    }

    private inline fun updatePlayer(block: PlayerModel.() -> PlayerModel) = updateFormState {
        val member = clubMember ?: ClubMemberModel(user = UserModel())
        val updatedPlayer = (member.player ?: PlayerModel()).block()
        copy(clubMember = member.copy(player = updatedPlayer))
    }

    private inline fun updateClub(block: ClubModel.() -> ClubModel) = updateFormState {
        copy(clubToCreate = (clubToCreate ?: ClubModel()).block())
    }


    fun onEmailChanged(email: String) = updateUser { copy(email = email) }
    fun onPasswordChanged(password: String) = updateFormState {
        val updatedClubMember = clubMember?.copy(
            user = clubMember.user.copy(password = password)
        ) ?: ClubMemberModel(user = UserModel(password = password))

        copy(
            clubMember = updatedClubMember
        )
    }
    fun onConfirmPasswordChanged(confirm: String) = updateFormState { copy(confirmPassword = confirm) }
    fun onNameChanged(name: String) = updateUser { copy(name = name) }
    fun onLastNameChanged(lastName: String) = updateUser { copy(lastName = lastName) }
    fun onPhoneNumberChanged(phone: String) = updateUser { copy(phoneNumber = phone) }
    fun onRoleChanged(role: String) = updateUser { copy(role = role) }
    fun onPositionChanged(position: String) = updatePlayer { copy(position = position) }
    fun onNumberChanged(number: Int?) = updatePlayer { copy(number = number ?: 0) }
    fun onClubNameChanged(name: String) = updateClub { copy(name = name) }
    fun onClubToCreateChanged(club: ClubModel) = updateFormState { copy(clubToCreate = club) }

    fun uploadImage(path: Any) {
        viewModelScope.launch(Dispatchers.IO) {
            val fileName = "user_${Clock.System.now().toEpochMilliseconds()}.jpg"
            val bytes = readImageBytes(path) ?: return@launch

            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

            val uploadedAt = buildString {
                append(now.dayOfMonth.toString().padStart(2, '0'))
                append("/")
                append(now.monthNumber.toString().padStart(2, '0'))
                append("/")
                append(now.year)
                append(" ")
                append(now.hour.toString().padStart(2, '0'))
                append(":")
                append(now.minute.toString().padStart(2, '0'))
                append(":")
                append(now.second.toString().padStart(2, '0'))
            }

            val metadata = ImageMetaDataModel(
                contentType = "image/jpeg",
                customMetaData = mapOf(
                    "uploadedAt" to uploadedAt,
                    "source" to getDeviceName()
                )
            )

//            uploadedImageUrl = uploadUserImageUseCase(fileName, bytes, metadata)
        }
    }

    fun onInviteCodeChanged(inviteCode: String) = updateFormState {
        val member = clubMember ?: ClubMemberModel(user = UserModel())
        copy(clubMember = member.copy(inviteCode = inviteCode))
    }

    fun onRegisterSubmit() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentForm = _state.value as? RegisterState.Form
            if (currentForm == null) {
                _state.update { RegisterState.Error("Formulario inválido") }
                return@launch
            }

            _state.update { RegisterState.Loading }
            try {
                val clubMember = currentForm.clubMember
                val clubToCreate = currentForm.clubToCreate
                val user = clubMember?.user ?: UserModel()
                val inviteCode = clubMember?.inviteCode?.trim()
                val email = user.email.trim()
                val password = user.password.trim()
                val name = user.name.trim()
                val lastName = user.lastName.trim()
                val role = user.role
                val number = user.number
                val position = user.position
                val imageUrl = uploadedImageUrl ?: user.imageUrl

                if (email.isBlank() || password.isBlank() || name.isBlank() || lastName.isBlank()) {
                    _state.update { RegisterState.Error("Por favor, completa todos los campos obligatorios.") }
                    return@launch
                }

                val userToRegister = user.copy(
                    email = email,
                    password = password,
                    name = name,
                    lastName = lastName,
                    role = role,
                    number = number,
                    position = position,
                    imageUrl = imageUrl,
                    createdAt = Clock.System.now().toEpochMilliseconds()
                )

                val club: ClubModel? = if (inviteCode.isNullOrBlank() && role == "admin") {
                    clubToCreate?.copy() ?: run {
                        _state.update { RegisterState.Error("Falta información del club") }
                        return@launch
                    }
                } else null

                val playerToCreate: PlayerModel? = if (role == "player") {
                    PlayerModel(
                        name = "$name $lastName",
                        position = position,
                        imageUrl = imageUrl,
                        isActive = true
                    )
                } else null

//                val result = registerUserUseCase(
//                    user = userToRegister,
//                    club = club,
//                    player = playerToCreate,
//                    inviteCode = inviteCode
//                )
//
//                result.onSuccess { (createdUser, createdClub, createdPlayer) ->
//                    _state.update {
//                        RegisterState.Success(
//                            user = createdUser,
//                            club = createdClub,
//                            player = createdPlayer
//                        )
//                    }
//                }.onFailure { exception ->
//                    _state.update {
//                        RegisterState.Error(exception.message ?: "Error desconocido al registrar.")
//                    }
//                }

            } catch (e: Exception) {
                _state.update {
                    RegisterState.Error("Error inesperado: ${e.message}")
                }
            }
        }
    }
}


sealed interface RegisterState {
    data object Idle : RegisterState
    data object Loading : RegisterState

    data class Form(
        val clubMember: ClubMemberModel? = null,
        val clubToCreate: ClubModel? = null,
        val confirmPassword: String = "",
    ) : RegisterState {

        private val user get() = clubMember?.user
        private val password get() = user?.password.orEmpty()
        private val email get() = user?.email.orEmpty()

        val passwordRules: List<Pair<String, Boolean>>
            get() = listOf(
                "Mínimo 8 caracteres" to (password.length >= 8),
                "Al menos una mayúscula" to password.any { it.isUpperCase() },
                "Al menos un número" to password.any { it.isDigit() },
                "Al menos un carácter especial" to password.any { !it.isLetterOrDigit() }
            )

        val isPasswordValid: Boolean
            get() = passwordRules.all { it.second }

        val doPasswordsMatch: Boolean
            get() = password == confirmPassword && password.isNotEmpty()

        val isEmailValid: Boolean
            get() = email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())

        val isFormValid: Boolean
            get() = isEmailValid && isPasswordValid && doPasswordsMatch
    }

    data class Success(
        val user: UserModel,
        val player: PlayerModel? = null,
        val club: ClubModel? = null,
        val message: String? = null
    ) : RegisterState

    data class Error(val message: String) : RegisterState
}