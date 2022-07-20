package com.github.ryjen.jokeapp.data.model

import com.github.ryjen.jokeapp.data.mapping.JokeMapper


internal fun JokeResponse.toDomain() = JokeMapper.invoke(this)