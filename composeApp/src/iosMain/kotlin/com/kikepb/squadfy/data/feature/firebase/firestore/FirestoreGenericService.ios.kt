package com.kikepb.squadfy.data.feature.firebase.firestore

import cocoapods.FirebaseFirestore.FIRFirestore
import cocoapods.FirebaseFirestore.FIRDocumentReference
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSDictionary
import platform.Foundation.NSMutableDictionary
import platform.Foundation.NSString
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.reflect.KClass

actual class FirestoreGenericService<T : Any> actual constructor(
    private val collectionPath: String,
    private val clazz: KClass<T>
) {
    private val firestore = FIRFirestore.firestore()

    actual suspend fun createItem(item: T, key: String?): String =
        suspendCancellableCoroutine { cont ->
            val collection = firestore.collectionWithPath(collectionPath)
            val ref: FIRDocumentReference = key?.let {
                collection.documentWithPath(it)
            } ?: collection.documentWithAutoID()

            ref.setData(item.toNSDictionary()) { error ->
                if (error != null) {
                    cont.resumeWithException(Exception(error.localizedDescription ?: "Firestore error"))
                } else {
                    cont.resume(ref.documentID)
                }
            }
        }
}

private fun Any.toNSDictionary(): NSDictionary {
    val dict = NSMutableDictionary()
    this::class.members
        .filter { it.parameters.isEmpty() }
        .forEach { member ->
            val value = member.call(this)
            if (value != null) {
                dict.setObject(value as NSObject, forKey = member.name as NSString)
            }
        }
    return dict
}