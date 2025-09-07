package com.kikepb.squadfy.di


import com.kikepb.squadfy.data.auth.AuthRepositoryAndroidImpl
import com.kikepb.squadfy.domain.feature.firebase.authentication.repository.AuthRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<AuthRepository> { AuthRepositoryAndroidImpl() }
}