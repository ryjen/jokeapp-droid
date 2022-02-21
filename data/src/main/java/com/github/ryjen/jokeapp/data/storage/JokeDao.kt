package com.github.ryjen.jokeapp.data.storage

import androidx.room.*
import com.github.ryjen.jokeapp.data.model.Joke
import kotlinx.coroutines.flow.Flow

private const val getJokeQuery = "SELECT * FROM jokes WHERE id = :jokeId"
private const val listJokesQuery = "SELECT * FROM jokes ORDER BY created"
private const val favoriteJokesQuery = "SELECT * FROM jokes WHERE isFavorite = 1 ORDER BY created"

@Dao
interface ObservableJokeDao {
    @Query(listJokesQuery)
    fun observeJokes(): Flow<Joke>

    @Query(listJokesQuery)
    fun getJokes(): Flow<List<Joke>>

    @Query(favoriteJokesQuery)
    fun observeFavoriteJokes(): Flow<Joke>

    @Query(favoriteJokesQuery)
    fun getFavoriteJokes(): Flow<List<Joke>>
}

@Dao
interface AsyncJokeDao {
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

    @Query(listJokesQuery)
    fun listJokes(): List<Joke>

    @Query(favoriteJokesQuery)
    fun getFavoriteJokes(): List<Joke>

}
