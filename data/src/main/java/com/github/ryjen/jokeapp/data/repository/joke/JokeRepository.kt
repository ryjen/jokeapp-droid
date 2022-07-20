package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.mapping.JokeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository as DomainJokeRepository

class JokeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val map: JokeMapper = JokeMapper
) : DomainJokeRepository {

    override fun getFavoriteJokes(): Flow<List<DomainJoke>> =
        localDataSource.getFavoriteJokes().map { list ->
            list.map { map(it) }
        }

    // random joke attempts remote and defaults to local cache
    override suspend fun getRandomJoke(): DomainJoke? = try {
        remoteDataSource.getRandomJoke()?.let { map(it) }
    } catch (e: Throwable) {
        localDataSource.getRandomJoke()?.let { map(it) }
    }

    // get joke attempts local and defaults to remote source
    override suspend fun getJoke(id: String): DomainJoke? = try {
        localDataSource.getJoke(id)?.let { map(it) }
    } catch (e: Throwable) {
        Timber.e("error fetching local joke", e)
        remoteDataSource.getJoke(id)?.let { map(it) }
    }

    // add or update a local cache joke to be a favorite
    override suspend fun addFavorite(joke: DomainJoke) = try {
        localDataSource.updateFavorite(joke.id, true)
        // add to remote service
    } catch (t: Throwable) {
        try {
            localDataSource.insertJoke(map(joke.copy(isFavorite = true)))
        } catch (e: Throwable) {
            Timber.e("error adding joke", e)
            throw e
        }
    }

    // update a local cached joke to longer be a favorite
    override suspend fun removeFavorite(joke: DomainJoke) = try {
        localDataSource.updateFavorite(joke.id, false)
        // remove from remote service
    } catch (e: Throwable) {
        Timber.e("error removing joke", e)
        throw e
    }

    // save a local cache of a joke
    override suspend fun cacheJoke(joke: DomainJoke) {
        localDataSource.insertJoke(map(joke))
    }
}
