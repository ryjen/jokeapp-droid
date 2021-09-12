package com.atimi.jokeapp.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.atimi.jokeapp.model.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}