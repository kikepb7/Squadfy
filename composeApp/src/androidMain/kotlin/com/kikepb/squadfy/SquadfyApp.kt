package com.kikepb.squadfy

import android.app.Application
import com.kikepb.squadfy.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SquadfyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(androidContext = this@SquadfyApp)
        }
    }
}