package com.github.ryjen.jokeapp.data.storage

import androidx.room.*
import com.github.ryjen.jokeapp.data.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

private const val getJokeQuery = "SELECT * FROM jokes WHERE id = :jokeId"


@Dao
interface ObservableJokeDao {
    @Query("SELECT * FROM jokes ORDER BY created")
    fun observeJokes(): Flow<Joke>

    @Query("SELECT * FROM jokes ORDER BY created")
    fun getJokes(): Flow<List<Joke>>

    @Query("SELECT * FROM jokes WHERE isFavorite = 1 ORDER BY created")
    fun observeFavoriteJokes(): Flow<Joke>

    @Query("SELECT * FROM jokes WHERE isFavorite = 1 ORDER BY created")
    fun getFavoriteJokes(): Flow<List<Joke>>
}

@Dao
interface AsyncJokeDao {
    @Query("SELECT * FROM jokes ORDER BY created")
    fun listJokes(): List<Joke>

    @Query("SELECT * FROM jokes WHERE isFavorite = 1 ORDER BY created")
    fun getFavoriteJokes(): List<Joke>

    @Query(getJokeQuery)
    suspend fun getJoke(jokeId: String): Joke?

    @Query("SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomJoke(): Joke?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJokes(vararg joke: Joke)

    @Delete
    suspend fun deleteJokes(vararg joke: Joke)
}

@Dao
interface SyncJokeDao {
    @Query(getJokeQuery)
    fun getJoke(jokeId: String): Joke?
}
