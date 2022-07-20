package com.github.ryjen.jokeapp.test.data.arch.module

import com.github.ryjen.jokeapp.data.storage.Joke
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.github.ryjen.jokeapp.data.storage.datesAdapter
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.koin.dsl.module

internal val fakeDatabaseModule = module {
    single {
        JokeDatabase(
            driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY),
            jokeAdapter = Joke.Adapter(
                createdAdapter = datesAdapter()
            )
        )
    }
}
