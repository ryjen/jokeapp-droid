package com.github.ryjen.jokeapp.meta.modules

import androidx.room.Room
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.data.repository.LocalDataSource
import com.github.ryjen.jokeapp.data.repository.RemoteDataSource
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import org.koin.dsl.module

internal val databaseModule = module {

    single {
        Room.databaseBuilder(get(), JokeDatabase::class.java, "joke-app-db")
            .build()
    }

    factory {
        get<JokeDatabase>().jokeDao()
    }

    factory {
        LocalDataSource(get())
    }

    factory {
        RemoteDataSource(get())
    }

    factory {
        JokeRepository(get(), get(), get())
    }
}
