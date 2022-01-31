package com.github.ryjen.jokeapp.data.repository

import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.repository.AsyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.ObservableJokeRepository
import com.github.ryjen.jokeapp.domain.repository.SyncJokeRepository
import com.github.ryjen.jokeapp.domain.usecase.GetNetworkAvailability
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val networkAvailable: GetNetworkAvailability,
) : ObservableJokeRepository, AsyncJokeRepository, SyncJokeRepository {

    private val randomJokeDelayTime: Long = 10000L

    override suspend fun getFavouriteJokes() = localDataSource.favouriteJokes

    override suspend fun getRandomJoke() = flow {
        while (true) {
            try {
                if (networkAvailable()) {
                    remoteDataSource.randomJoke
                        .onEach {
                            localDataSource.cacheJoke(it)
                        }
                        .firstOrNull()?.let {
                            emit(it)
                        }
                } else {
                    localDataSource.randomJoke()
                        .firstOrNull()?.let {
                            emit(it)
                        }
                }
            } catch (e: Throwable) {
                Timber.e("error fetching remote random joke", e)
                localDataSource.randomJoke()
                    .firstOrNull()?.let {
                        emit(it)
                    }
            }
            delay(randomJokeDelayTime)
        }
    }

    override suspend fun getJoke(jokeId: String): Joke? =
        try {
            localDataSource.getJoke(jokeId).firstOrNull()
        } catch (e: Throwable) {
            Timber.e("error fetching local joke", e)
            remoteDataSource.getJoke(jokeId)
        }

    override suspend fun addFavorite(joke: Joke) =
        try {
            localDataSource.addFavorite(joke)
            // add to remote service
        } catch (e: Throwable) {
            Timber.e("error adding joke", e)
            throw e
        }

    override suspend fun removeFavorite(joke: Joke) =
        try {
            localDataSource.removeFavorite(joke)
            // remove from remote service
        } catch (e: Throwable) {
            Timber.e("error removing joke", e)
            throw e
        }

}
