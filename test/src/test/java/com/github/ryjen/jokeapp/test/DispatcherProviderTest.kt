package com.github.ryjen.jokeapp.test

import com.github.ryjen.jokeapp.meta.coroutine.DispatcherProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import org.junit.Test
import java.util.concurrent.Executors

class DispatcherProviderTest {

    @Test
    fun `has correct defaults`() {
        val provider = object : DispatcherProvider {}

        assertThat(provider.default()).isEqualTo(Dispatchers.Default)
        assertThat(provider.io()).isEqualTo(Dispatchers.IO)
        assertThat(provider.main()).isEqualTo(Dispatchers.Main)
        assertThat(provider.unconfined()).isEqualTo(Dispatchers.Unconfined)
    }

    @Test
    fun `can set dispatchers provided`() {
        val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        val provider = object : DispatcherProvider {
            override fun default() = dispatcher
            override fun main() = dispatcher
            override fun io() = dispatcher
            override fun unconfined() = dispatcher
        }

        assertThat(provider.default()).isEqualTo(dispatcher)
        assertThat(provider.io()).isEqualTo(dispatcher)
        assertThat(provider.main()).isEqualTo(dispatcher)
        assertThat(provider.unconfined()).isEqualTo(dispatcher)
    }
}
