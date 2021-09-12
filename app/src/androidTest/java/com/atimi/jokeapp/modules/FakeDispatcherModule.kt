package com.atimi.jokeapp.modules

import com.atimi.jokeapp.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatcherModule::class]
)
@Module
class FakeDispatcherModule {

    companion object {
        @ExperimentalCoroutinesApi
        val testDispatcher = TestCoroutineDispatcher()
    }

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun providesFakeDispatchers(): DispatcherProvider {
        return object : DispatcherProvider {
            override fun default(): CoroutineDispatcher = testDispatcher
            override fun io(): CoroutineDispatcher = testDispatcher
            override fun main(): CoroutineDispatcher = testDispatcher
            override fun unconfined(): CoroutineDispatcher = testDispatcher
        }
    }

    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(dispatchers: DispatcherProvider): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + dispatchers.default())
    }
}