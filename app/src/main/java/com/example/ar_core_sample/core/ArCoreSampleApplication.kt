package com.example.ar_core_sample.core

import android.app.Application
import com.example.ar_core_sample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArCoreSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //  Koin
        startKoin {
            androidContext(this@ArCoreSampleApplication)
            modules(appModule)
        }
    }
}