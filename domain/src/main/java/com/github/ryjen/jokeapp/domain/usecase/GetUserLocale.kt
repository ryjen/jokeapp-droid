package com.github.ryjen.jokeapp.domain.usecase

import java.util.*

class GetUserLocale {

    operator fun invoke(): Locale {
        return Locale.getDefault()
    }
}
