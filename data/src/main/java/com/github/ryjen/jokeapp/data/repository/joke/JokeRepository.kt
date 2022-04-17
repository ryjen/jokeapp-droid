package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.model.Joke
import com.github.ryjen.jokeapp.domain.repository.joke.io.AsyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.ObservableJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.SyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.LocalJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.RemoteJokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import com.github.ryjen.jokeapp.domain.mapping.JokeMapper as DomainMapper
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository as DomainRepository

class JokeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val jokeMapper: DomainMapper<Joke> = com.github.ryjen.jokeapp.data.mapping.JokeMapper
) : DomainRepository, AsyncJokeRepository {

    // asynchronous io only
    override val async: AsyncJokeRepository = this

    // observable cold flows only
    override val observable = object : ObservableJokeRepository {
        override fun getFavoriteJokes(): Flow<List<DomainJoke>> =
            localDataSource.observable.getFavoriteJokes().map { list ->
                list.map { joke -> jokeMapper(joke) }
            }
    }

    // synchronous io only
    override val sync = object : SyncJokeRepository {
        override fun getJoke(jokeId: String): DomainJoke? =
            localDataSource.sync.getJoke(jokeId)?.let { jokeMapper(it) }
    }

    // network only calls only
    override val remote: RemoteJokeRepository = object : RemoteJokeRepository {
        override suspend fun getJoke(jokeId: String) = remoteDataSource.getJoke(jokeId)
        override suspend fun getRandomJoke() = remoteDataSource.randomJoke()
    }

    // local only calls only
    override val local: LocalJokeRepository = object : LocalJokeRepository {
        override suspend fun addFavorite(joke: DomainJoke) =
            localDataSource.updateFavorite(jokeMapper(joke))

        override suspend fun removeFavorite(joke: DomainJoke) =
            localDataSource.async.deleteJokes(jokeMapper(joke))

        override suspend fun cacheJoke(joke: DomainJoke) =
            localDataSource.async.insertJokes(jokeMapper(joke))
    }

    // random joke attempts remote and defaults to local cache
    override suspend fun getRandomJoke(): DomainJoke? = try {
        remote.getRandomJoke()
    } catch (e: Throwable) {
        async.getRandomJoke()
    }

    // get joke attempts local and defaults to remote source
    override suspend fun getJoke(jokeId: String): DomainJoke? = try {
        async.getJoke(jokeId)
    } catch (e: Throwable) {
        Timber.e("error fetching local joke", e)
        remote.getJoke(jokeId)
    }

    // add or update a local cache joke to be a favorite
    override suspend fun addFavorite(joke: DomainJoke) = try {
        local.addFavorite(joke)
        // add to remote service
    } catch (e: Throwable) {
        Timber.e("error adding joke", e)
        throw e
    }

    // update a local cached joke to longer be a favorite
    override suspend fun removeFavorite(joke: DomainJoke) = try {
        local.removeFavorite(joke)
        // remove from remote service
    } catch (e: Throwable) {
        Timber.e("error removing joke", e)
        throw e
    }

    // save a local cache of a joke
    override suspend fun cacheJoke(joke: DomainJoke) {
        local.cacheJoke(joke)
    }
}
