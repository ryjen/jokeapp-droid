package com.github.ryjen.jokeapp.ui.arch.redux

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.ui.arch.Failure

interface ReduxStateWithError: ReduxState {
    val error: Failure?
}
