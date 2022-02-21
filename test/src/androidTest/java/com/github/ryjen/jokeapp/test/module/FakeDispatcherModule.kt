package com.github.ryjen.jokeapp.test.module

import com.github.ryjen.jokeapp.meta.coroutine.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.dsl.module
import java.util.concurrent.Executors

internal val fakeDispatcherModule = module {

    single<DispatcherProvider> {
        val testDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

        object : DispatcherProvider {
            override fun default(): CoroutineDispatcher = testDispatcher
            override fun io(): CoroutineDispatcher = testDispatcher
            override fun main(): CoroutineDispatcher = testDispatcher
            override fun unconfined(): CoroutineDispatcher = testDispatcher
        }
    }
}
