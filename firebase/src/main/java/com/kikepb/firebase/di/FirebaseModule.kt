package com.kikepb.firebase.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import com.google.firebase.messaging.FirebaseMessaging
import com.kikepb.firebase.analytics.FirebaseAnalyticsService
import com.kikepb.firebase.authentication.FirebaseAuthService
import com.kikepb.firebase.notification.TopicsService
import com.kikepb.firebase.storage.FirebaseStorageService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val firebaseModule = module {
    single { Firebase.firestore }
    single { Firebase.database.reference }
    single { FirebaseMessaging.getInstance() }
    single { TopicsService(get()) }
    factory { FirebaseAuthService(get()) }
    factory { FirebaseStorageService(get()) }
    factory { FirebaseAnalyticsService(get()) }
    single<FirebaseAnalytics> { FirebaseAnalytics.getInstance(androidContext()) }
}