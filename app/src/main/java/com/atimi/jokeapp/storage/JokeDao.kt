package com.atimi.jokeapp.storage

import androidx.room.*
import com.atimi.jokeapp.model.Joke

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokes ORDER BY created")
    suspend fun getJokes(): List<Joke>

    @Query("SELECT * FROM jokes WHERE id = :jokeId")
    suspend fun getJoke(jokeId: String): Joke?

    @Query("SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomJoke(): Joke?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJokes(vararg joke: Joke)

    @Delete
    suspend fun deleteJokes(vararg joke: Joke)
}