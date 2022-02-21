package com.github.ryjen.jokeapp.domain.arch.module

import com.github.ryjen.jokeapp.meta.arch.module.useCaseModule
import org.koin.core.context.loadKoinModules

val domainModules = listOf(
    useCaseModule
)

private val loadDomainModules by lazy {
    loadKoinModules(domainModules)
}

fun injectDomainModules() = loadDomainModules
