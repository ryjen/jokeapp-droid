package com.github.ryjen.jokeapp.ui

import androidx.lifecycle.ViewModel
import com.github.ryjen.jokeapp.domain.usecase.GetUserLocale
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUserLocale: GetUserLocale
) : ViewModel() {

    val userLocale: Locale
       get() = getUserLocale()
}
