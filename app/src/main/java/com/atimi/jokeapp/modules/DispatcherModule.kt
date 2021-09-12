package com.atimi.jokeapp.modules

import com.atimi.jokeapp.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {

    @Singleton
    @Provides
    fun providesDispatchers(): DispatcherProvider {
        return object : DispatcherProvider {}
    }

    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(dispatchers: DispatcherProvider): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + dispatchers.default())
    }
}