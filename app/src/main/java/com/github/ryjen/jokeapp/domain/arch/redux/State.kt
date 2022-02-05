package com.github.ryjen.jokeapp.domain.arch.redux

import com.github.ryjen.jokeapp.domain.model.Failure

interface ReduxState

interface ErrorState: ReduxState {
    val error: Failure?
}
