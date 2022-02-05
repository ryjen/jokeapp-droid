package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.dsl.module

internal val fakeDispatcherModule = module {

    single<DispatcherProvider> {
        val testDispatcher = TestCoroutineDispatcher()

        object : DispatcherProvider {
            override fun default(): CoroutineDispatcher = testDispatcher
            override fun io(): CoroutineDispatcher = testDispatcher
            override fun main(): CoroutineDispatcher = testDispatcher
            override fun unconfined(): CoroutineDispatcher = testDispatcher
        }
    }
}
