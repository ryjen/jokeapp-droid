package com.github.ryjen.jokeapp.data.arch.module

import com.github.ryjen.jokeapp.meta.arch.modules.networkSourceModule
import org.koin.core.context.loadKoinModules

val dataModules = listOf(
    localSourceModule, networkSourceModule
)

private val loadDataModules by lazy {
    loadKoinModules(dataModules)
}

fun injectDataModules() = loadDataModules
