package com.github.ryjen.jokeapp.test.module

import com.github.ryjen.jokeapp.test.data.arch.module.fakeDatabaseModule
import com.github.ryjen.jokeapp.test.module.fakeDispatcherModule

val fakeAppModules =
    fakeDatabaseModule + fakeDispatcherModule
