package com.atimi.jokeapp.storage

import android.util.Log
import com.atimi.jokeapp.model.Joke
import com.atimi.jokeapp.service.JokeService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRepository @Inject constructor(
    private val jokeDao: JokeDao,
    private val service: JokeService,
) {
    companion object {
        const val TAG = "JokeRepository"
    }

    suspend fun getFavouriteJokes() = jokeDao.getJokes()

    suspend fun getRandomJoke(): Joke? =
        try {
            // prefer remote joke
            service.getRandomJoke()
        } catch (e: Exception) {
            Log.e(TAG, "error fetching remote random joke", e)
            // default to local
            jokeDao.getRandomJoke()
        }

    suspend fun getJoke(jokeId: String): Joke? =
        try {
            jokeDao.getJoke(jokeId)
        } catch (e: Exception) {
            Log.e(TAG, "error fetching local joke", e)
            service.getJoke(jokeId)
        }


    suspend fun addJokes(vararg joke: Joke) =
        try {
            jokeDao.insertJokes(*joke)
            // add to remote service
        } catch (e: Exception) {
            Log.e(TAG, "error adding joke", e)
        }


    suspend fun removeJokes(vararg joke: Joke) =
        try {
            jokeDao.deleteJokes(*joke)
            // remove from remote service
        } catch (e: Exception) {
            Log.e(TAG, "error removing joke", e)
        }

}