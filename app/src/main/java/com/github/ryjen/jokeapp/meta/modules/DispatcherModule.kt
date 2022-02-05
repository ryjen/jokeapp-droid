package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider
import org.koin.dsl.module

internal val dispatcherModule = module {
    single<DispatcherProvider> {
        object : DispatcherProvider {}
    }
}
