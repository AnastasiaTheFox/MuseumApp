package com.akomissarova.testmuseum

import android.app.Application
import com.akomissarova.testmuseum.di.artCollectionsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MuseumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MuseumApplication)
            modules(artCollectionsModule)
        }
    }
}