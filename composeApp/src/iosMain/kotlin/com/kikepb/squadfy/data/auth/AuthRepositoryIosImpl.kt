package com.kikepb.squadfy.data.auth

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRAuthStateDidChangeListenerHandle
import cocoapods.FirebaseAuth.FIRUser
import com.kikepb.squadfy.domain.common.AuthErrorCode
import com.kikepb.squadfy.domain.common.Either
import com.kikepb.squadfy.domain.common.FailureModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.model.AuthUserModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.repository.AuthRepository
import com.kikepb.squadfy.domain.feature.user.model.UserModel
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSError
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class, ExperimentalCoroutinesApi::class)
class AuthRepositoryIosImpl : AuthRepository {
    private val auth = FIRAuth.auth()
    private val _state = MutableStateFlow<AuthUserModel?>(null)
    private var handle: FIRAuthStateDidChangeListenerHandle? = null

    init {
        handle = auth.addAuthStateDidChangeListener { _, user: FIRUser? ->
            _state.value = user?.let { AuthUserModel(uid = it.uid(), email = it.email())}
        }
    }

    override val authState: Flow<AuthUserModel?> = _state.asStateFlow()

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Either<FailureModel, AuthUserModel> {
        return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { continuation ->
                auth.signInWithEmail(email = email, password = password) { result: FIRAuthDataResult?, error: NSError? ->
                    if (!continuation.isActive) return@signInWithEmail
                    when {
                        error != null -> {
                            continuation.resume(Either.Error(FailureModel.GenericError(message = error.localizedDescription)))
                        }
                        result?.user() == null -> {
                            continuation.resume(Either.Error(FailureModel.GenericError(message = "ERROR")))
                        }
                        else -> {
                            val user = result.user()
                            continuation.resume(Either.Success(AuthUserModel(uid = user.uid(), email = user.email())))
                        }
                    }
                }
            }
        }
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String, userModel: UserModel): Either<FailureModel, String> {
        return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { continuation ->
                auth.createUserWithEmail(email = email, password = password) { result, error ->
                    if (!continuation.isActive) return@createUserWithEmail
                    when {
                        error != null -> {
                            continuation.resume(
                                Either.Error(FailureModel.AuthError(AuthErrorCode.UNKNOWN_AUTH_ERROR, error.localizedDescription))
                            )
                        }
                        result?.user() == null -> {
                            continuation.resume(
                                Either.Error(FailureModel.AuthError(AuthErrorCode.UNKNOWN_AUTH_ERROR, "Usuario nulo"))
                            )
                        }
                        else -> {
                            val user = result.user()
                            user.sendEmailVerificationWithCompletion { verificationError ->
                                if (verificationError != null) {
                                    continuation.resume(
                                        Either.Error(FailureModel.GenericError(verificationError.localizedDescription))
                                    )
                                } else {
                                    continuation.resume(Either.Success(user.uid()))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun logout() { auth.signOut(null) }
}