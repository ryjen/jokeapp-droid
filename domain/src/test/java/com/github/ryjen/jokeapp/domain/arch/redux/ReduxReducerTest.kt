package com.github.ryjen.jokeapp.domain.arch.redux

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ReduxReducerTest {

    data class State(val test: Int) : ReduxState

    sealed class Actions : ReduxAction {
        object Test : Actions()
    }

    interface UseCase {
        operator fun invoke(param: Int)
    }

    @Test
    fun `can combine two reducers`() {
        val useCase = mockk<UseCase>()

        every { useCase.invoke(any()) } returns Unit

        val reduceState: ReduxReducer<State, Actions> = { state, action ->
            when (action) {
                Actions.Test -> state.copy(test = state.test + 1)
            }
        }

        val reduceEffect: ReduxReducer<State, Actions> = { state, action ->
            when (action) {
                Actions.Test -> state.apply {
                    useCase(test)
                }
            }
        }

        val reducer = combineReducers(reduceState, reduceEffect)

        reducer.invoke(State(test = 0), Actions.Test)

        verify { useCase.invoke(1) }
    }
}
