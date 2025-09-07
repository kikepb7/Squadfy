package com.kikepb.squadfy.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikepb.squadfy.domain.common.AuthErrorCode
import com.kikepb.squadfy.domain.common.Either
import com.kikepb.squadfy.domain.common.FailureModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.model.AuthUserModel
import com.kikepb.squadfy.domain.feature.firebase.authentication.repository.AuthRepository
import com.kikepb.squadfy.domain.feature.user.model.UserModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryAndroidImpl : AuthRepository {
    private val auth = Firebase.auth

    override val authState: Flow<AuthUserModel?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser?.let {
                AuthUserModel(uid = it.uid, email = it.email)
            }
            trySend(element = user)
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Either<FailureModel, AuthUserModel> =
        runCatching {
            auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            if (user != null) {
                Either.Success(AuthUserModel(user.uid, user.email))
            } else {
                Either.Error(FailureModel.AuthError(AuthErrorCode.UNKNOWN_AUTH_ERROR, "UID nulo"))
            }
        }.getOrElse {
            Either.Error(FailureModel.AuthError(code = AuthErrorCode.UNKNOWN_AUTH_ERROR, message = it.message.toString()))
        }
/*    catch (e: Exception) {
            Either.Error(FailureModel.GenericError(e.message ?: "Error desconocido"))
        }*/
//        runCatching {
//            auth.createUserWithEmailAndPassword(email, password).await()
//            val user = auth.currentUser ?: error("Usuario no encontrado")
//
//                AuthResult.Success(
//                    user = AuthUser(
//                        uid = user.uid,
//                        email = user.email
//                    )
//                )
//        }.getOrElse {
//            AuthResult.Error(
//                    message = it.message.orEmpty(),
//                cause = it
//                )

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String,
        userModel: UserModel
    ): Either<FailureModel, String> {
        TODO("Not yet implemented")
    }

    override suspend fun logout() = auth.signOut()
}