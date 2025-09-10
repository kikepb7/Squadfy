package com.kikepb.squadfy.data.feature.firebase.firestore

import kotlin.reflect.KClass

expect class FirestoreGenericService<T: Any>(
    collectionPath: String,
    clazz: KClass<T>
) {
    suspend fun createItem(item: T, key: String? = null): String
}