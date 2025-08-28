package com.kikepb.squadfy.di

import com.kikepb.squadfy.data.di.dataModule
import com.kikepb.squadfy.domain.di.domainModule
import com.kikepb.squadfy.ui.di.uiModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dataModule,
            domainModule,
            uiModule,
            platformModule()
        )
    }
}