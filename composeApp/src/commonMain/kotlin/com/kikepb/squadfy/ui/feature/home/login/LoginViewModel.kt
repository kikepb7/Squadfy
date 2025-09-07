package com.kikepb.squadfy.ui.feature.home.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikepb.squadfy.domain.common.Either
import com.kikepb.squadfy.domain.common.FailureModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.usecases.LoginWithEmailPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginWithEmailPasswordUseCase: LoginWithEmailPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val state= _state.asStateFlow()

    fun signUpWithEmail(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { SignUpState.Loading }

            when (val result = loginWithEmailPasswordUseCase(email = email, password = password)) {
                is Either.Error -> _state.update {
                    SignUpState.Error(FailureModel.GenericError(result.error.toString()))
                }
                is Either.Success -> _state.update {
                    SignUpState.Success(userId = result.data.uid, clubId = result.data.uid)
                }
            }
        }
    }

//    fun registerWithGoogle(idToken: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _state.update { SignUpState.Loading }
//
//            when (val result = registerUserWithGoogleUseCase(idToken = idToken)) {
//                is Either.Success -> {
//                    val userId = result.data
//                    val user = getUserByIdUseCase(userId).first()
//                    _state.update { SignUpState.Success(userId = user?.id.orEmpty(), clubId = user?.clubId.orEmpty()) }
//                }
//
//                is Either.Error -> {
//                    _state.update { SignUpState.Error(failure = FailureModel.GenericError(result.error)) }
//                }
//            }
//        }
//    }


//    fun signUpWithPhoneNumber(phoneNumber: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _state.value = SignUpState.Loading
//
//            when (val result = googleSignInClientProvider.signUpWithPhoneVerification(phoneNumber = phoneNumber, activity = )) {
//                is Either.Success -> _state.value = SignUpState.CodeSent
//                is Either.Error -> _state.value = SignUpState.Error(message = result.error)
//            }
//        }
//    }

//    fun verifyOtp(verificationId: String, otp: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _state.value = SignUpState.Loading
//
//            when (val result = googleSignInClientProvider.verifyOtpCode(verificationId, otp)) {
//                is Either.Success -> {
//                    val user = getUserByIdUseCase(result.data).first()
//                    _state.value = SignUpState.Success(userId = user?.id.orEmpty(), clubId = user?.clubId.orEmpty())
//                }
//                is Either.Error -> _state.value = SignUpState.Error(result.error)
//            }
//        }
//    }
}

sealed interface SignUpState {
    data object Idle : SignUpState
    data object Loading : SignUpState
    data object CodeSent : SignUpState
    data class Success(val userId: String, val clubId: String) : SignUpState
    data class Error(val failure: FailureModel) : SignUpState
}