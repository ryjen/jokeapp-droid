package com.github.ryjen.jokeapp.meta.arch.module

import org.koin.core.context.loadKoinModules

val metaModules = listOf(
    dispatcherModule
)

private val loadMetaModules by lazy {
    loadKoinModules(metaModules)
}

fun injectMetaModules() = loadMetaModules
