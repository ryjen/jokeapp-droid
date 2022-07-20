package com.github.ryjen.jokeapp.domain.arch.module

import org.koin.core.context.loadKoinModules

val domainModules = listOf(
    useCaseModule
)

private val loadDomainModules by lazy {
    loadKoinModules(domainModules)
}

fun injectDomainModules() = loadDomainModules
