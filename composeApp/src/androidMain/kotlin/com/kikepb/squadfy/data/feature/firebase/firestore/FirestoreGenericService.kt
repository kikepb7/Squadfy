package com.kikepb.squadfy.data.feature.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.reflect.KClass

actual class FirestoreGenericService<T : Any> actual constructor(
    private val collectionPath: String,
    private val clazz: KClass<T>
) {
    private val firestore = FirebaseFirestore.getInstance()

    actual suspend fun createItem(item: T, key: String?): String {
        val ref = key?.let { firestore.collection(collectionPath).document(it) }
            ?: firestore.collection(collectionPath).document()
        ref.set(item).await()
        return ref.id
    }
}