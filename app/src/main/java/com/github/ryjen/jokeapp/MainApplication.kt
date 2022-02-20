package com.github.ryjen.jokeapp

import android.app.Application
import com.github.ryjen.jokeapp.data.arch.module.dataModules
import com.github.ryjen.jokeapp.domain.arch.module.domainModules
import com.github.ryjen.jokeapp.meta.arch.modules.metaModules
import com.github.ryjen.jokeapp.ui.arch.module.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            //androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                metaModules +
                dataModules +
                        domainModules +
                        uiModules

            )
        }
    }
}
