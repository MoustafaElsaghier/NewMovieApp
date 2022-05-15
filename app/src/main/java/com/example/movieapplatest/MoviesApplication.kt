package com.example.movieapplatest

import android.app.Application
import com.example.movieapplatest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MoviesApplication)
            modules(utilModule)
            modules(networkModule)
            modules(repoModule)
            modules(viewModelModule)
            modules(adapters)
        }
    }

}