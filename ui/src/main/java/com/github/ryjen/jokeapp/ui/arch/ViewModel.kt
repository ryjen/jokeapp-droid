package com.github.ryjen.jokeapp.ui.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

abstract class ReduxViewModel<State : ReduxState, VState : ViewState, Action : ReduxAction> :
    ViewModel(),
    ReduxDispatcher<Action> {

    protected abstract val store: ReduxStore<State, Action>

    fun state(): StateFlow<VState> = store.stateAsStateFlow().asViewState()

    private fun StateFlow<State>.asViewState() = map(::mapViewState).stateIn(
        viewModelScope, SharingStarted.Eagerly, mapViewState(this.value)
    )

    abstract fun mapViewState(state: State): VState

    override fun dispatch(action: Action) {
        store.dispatch(action)
    }
}
