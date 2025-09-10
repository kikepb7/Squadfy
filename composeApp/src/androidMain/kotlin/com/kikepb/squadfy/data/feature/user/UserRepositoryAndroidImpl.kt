package com.kikepb.squadfy.data.feature.user

import com.google.firebase.firestore.FirebaseFirestore
import com.kikepb.squadfy.domain.feature.user.model.UserModel
import com.kikepb.squadfy.domain.feature.user.repository.UserRepository

class UserRepositoryAndroidImpl(
    private val firestore: FirebaseFirestore
) : UserRepository {

    companion object {
        const val USER_PATH = "users"
    }

//    private val service = FirebaseFirestoreGenericService(
//        firestore = firestore,
//        collectionPath = USER_PATH,
//        clazz = UserDto::class.java
//    )

    override suspend fun createUser(user: UserModel): String {
        return ""
    }
}