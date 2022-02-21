package com.github.ryjen.jokeapp.meta.arch.module

import com.github.ryjen.jokeapp.meta.coroutine.DispatcherProvider
import org.koin.dsl.module

internal val dispatcherModule = module {
    single<DispatcherProvider> {
        object : DispatcherProvider {}
    }
}
