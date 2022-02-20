package com.github.ryjen.jokeapp.meta.arch.modules

import com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.dsl.module

internal val fakeDispatcherModule = module {

    single<com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider> {
        val testDispatcher = TestCoroutineDispatcher()

        object : com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider {
            override fun default(): CoroutineDispatcher = testDispatcher
            override fun io(): CoroutineDispatcher = testDispatcher
            override fun main(): CoroutineDispatcher = testDispatcher
            override fun unconfined(): CoroutineDispatcher = testDispatcher
        }
    }
}
