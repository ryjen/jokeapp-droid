package com.github.ryjen.jokeapp.ui.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicReference

class LazyComposable<T : ViewModel>(
    private val builder: @Composable () -> T
) {
    private var item = AtomicReference<T?>(null)

    @Composable
    operator fun invoke(): T = with(item) {
        if (get() == null) {
            set(builder())
        }
        return get() ?: throw IllegalStateException("no lazy composable built")
    }
}
