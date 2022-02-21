package com.github.ryjen.jokeapp.test.data.arch.module

import androidx.room.Room
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import org.koin.dsl.module

internal val fakeDatabaseModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            get(), JokeDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}
