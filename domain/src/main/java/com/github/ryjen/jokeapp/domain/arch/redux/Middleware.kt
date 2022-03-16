package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// thunk to inject as middleware, can be async or sync
interface ReduxThunk<State, Action> {
    interface Sync<S, A> : ReduxThunk<S, A> {
        operator fun invoke(state: S, action: A, dispatcher: ReduxDispatcher<A>)
    }

    interface Async<S, A> : ReduxThunk<S, A> {
        suspend operator fun invoke(state: S, action: A, dispatcher: ReduxDispatcher<A>)
    }
}

// middleware to apply to the store.  async thunks are scoped
class ReduxMiddleware<State : ReduxState, Action : ReduxAction>(
    private val scope: CoroutineScope,
    private vararg val thunks: ReduxThunk<State, Action>
) {

    operator fun invoke(state: State, action: Action, dispatcher: ReduxDispatcher<Action>) {
        for (thunk in thunks) {
            when (thunk) {
                is ReduxThunk.Sync -> thunk(state, action, dispatcher)
                is ReduxThunk.Async -> scope.launch {
                    thunk(state, action, dispatcher)
                }
            }
        }
    }
}

// create an async thunk
fun <S : ReduxState, A : ReduxAction> createAsyncThunk(block: suspend (S, A, ReduxDispatcher<A>) -> Unit) =
    object : ReduxThunk.Async<S, A> {
        override suspend fun invoke(state: S, action: A, dispatcher: ReduxDispatcher<A>) =
            block(state, action, dispatcher)
    }

// apply thunks to middleware
fun <S : ReduxState, A : ReduxAction> applyMiddleware(
    scope: CoroutineScope,
    vararg thunks: ReduxThunk<S, A>
) =
    ReduxMiddleware(scope, *thunks)
