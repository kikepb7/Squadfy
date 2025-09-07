package com.kikepb.squadfy.domain.di

import com.kikepb.squadfy.domain.feature.firebase.authentication.usecases.LogoutUseCase
import com.kikepb.squadfy.domain.feature.firebase.authentication.usecases.ObserveAuthState
import com.kikepb.squadfy.domain.feature.firebase.authentication.usecases.LoginWithEmailPasswordUseCase
import org.koin.dsl.module

val domainModule = module {
    // Auth / User registration
    factory { LoginWithEmailPasswordUseCase(get()) }
    factory { ObserveAuthState(get()) }

    // Session
    factory { LogoutUseCase(get()) }

}