package com.github.ryjen.jokeapp.data.storage

import androidx.room.*
import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokes ORDER BY created")
    fun getJokes(): Flow<Joke>

    suspend fun listJokes() = getJokes().toList()

    @Query("SELECT * FROM jokes WHERE isFavorite = 1 ORDER BY created")
    fun getFavoriteJokes(): Flow<Joke>

    suspend fun listFavoriteJokes() = getFavoriteJokes().toList()

    @Query("SELECT * FROM jokes WHERE id = :jokeId")
    suspend fun getJoke(jokeId: String): Joke?

    @Query("SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomJoke(): Joke?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJokes(vararg joke: Joke)

    @Delete
    suspend fun deleteJokes(vararg joke: Joke)
}
