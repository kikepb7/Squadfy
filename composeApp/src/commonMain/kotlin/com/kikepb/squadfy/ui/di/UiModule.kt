package com.kikepb.squadfy.ui.di

import com.kikepb.squadfy.ui.feature.home.login.LoginViewModel
import com.kikepb.squadfy.ui.feature.home.logout.LogoutViewModel
import com.kikepb.squadfy.ui.feature.home.register.RegisterStepViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { LogoutViewModel(get()) }
    viewModel { RegisterStepViewModel() }
}