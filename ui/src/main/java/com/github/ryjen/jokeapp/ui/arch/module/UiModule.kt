package com.github.ryjen.jokeapp.ui.arch.module

import org.koin.core.context.loadKoinModules

val uiModules = listOf(
    viewModelModule
)

private val loadUiModules by lazy {
    loadKoinModules(uiModules)
}

fun injectUiModules() = loadUiModules
