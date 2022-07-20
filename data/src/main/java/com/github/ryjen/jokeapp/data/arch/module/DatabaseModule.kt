package com.github.ryjen.jokeapp.data.arch.module

import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.data.repository.joke.LocalDataSource
import com.github.ryjen.jokeapp.data.repository.joke.RemoteDataSource
import com.github.ryjen.jokeapp.data.storage.Joke
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.github.ryjen.jokeapp.data.storage.JokeDatabaseName
import com.github.ryjen.jokeapp.data.storage.datesAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.dsl.module
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository as DomainJokeRepository

internal val localSourceModule = module {

    single {
        JokeDatabase(
            AndroidSqliteDriver(JokeDatabase.Schema, get(), JokeDatabaseName),
            jokeAdapter = Joke.Adapter(
                createdAdapter = datesAdapter()
            )
        )
    }

    factory {
        LocalDataSource(get())
    }

    factory {
        RemoteDataSource(get())
    }

    factory<DomainJokeRepository> {
        JokeRepository(get(), get())
    }
}
