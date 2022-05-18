package com.github.ryjen.jokeapp.data.arch.module

import androidx.room.Room
import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.data.repository.joke.LocalDataSource
import com.github.ryjen.jokeapp.data.repository.joke.RemoteDataSource
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.github.ryjen.jokeapp.data.storage.JokeDatabaseName
import org.koin.dsl.module
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository as DomainJokeRepository

internal val localSourceModule = module {

    single {
        Room.databaseBuilder(get(), JokeDatabase::class.java, JokeDatabaseName)
            .build()
    }

    factory {
        val db = get<JokeDatabase>()
        LocalDataSource(
            db.observableJokeDao(),
            db.asyncJokeDao(),
            db.syncJokeDao()
        )
    }

    factory {
        RemoteDataSource(get())
    }

    factory<DomainJokeRepository> {
        JokeRepository(get(), get())
    }
}
