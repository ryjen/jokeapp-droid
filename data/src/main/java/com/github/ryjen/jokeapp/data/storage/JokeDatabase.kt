package com.github.ryjen.jokeapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.ryjen.jokeapp.data.model.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun observableJokeDao(): ObservableJokeDao
    abstract fun asyncJokeDao(): AsyncJokeDao
    abstract fun syncJokeDao(): SyncJokeDao
}
