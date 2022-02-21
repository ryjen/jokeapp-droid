package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.model.toData
import com.github.ryjen.jokeapp.data.model.toDomain
import com.github.ryjen.jokeapp.domain.repository.joke.io.AsyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.ObservableJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.SyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.LocalJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.RemoteJokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository as DomainRepository

class JokeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : DomainRepository, AsyncJokeRepository {

    // concurrency variations can be remote, local or both
    override val async: AsyncJokeRepository = this

    override val observable = object: ObservableJokeRepository {
        override fun getFavoriteJokes(): Flow<List<DomainJoke>> =
            localDataSource.observable.getFavoriteJokes().map { list ->
                list.map { joke -> joke.toDomain() }
            }
    }

    override val sync = object: SyncJokeRepository {
        override fun getJoke(jokeId: String): DomainJoke? =
            localDataSource.sync.getJoke(jokeId)?.toDomain()
    }

    // network only calls
    override val remote: RemoteJokeRepository = object : RemoteJokeRepository {
        override suspend fun getJoke(jokeId: String) = remoteDataSource.getJoke(jokeId)
        override suspend fun getRandomJoke() = remoteDataSource.randomJoke()
    }

    // local only calls
    override val local: LocalJokeRepository = object : LocalJokeRepository {
        override suspend fun addFavorite(joke: DomainJoke) =
            localDataSource.updateFavorite(joke.toData())

        override suspend fun removeFavorite(joke: DomainJoke) =
            localDataSource.async.deleteJokes(joke.toData())

        override suspend fun saveJoke(joke: DomainJoke) =
            localDataSource.async.insertJokes(joke.toData())

        override suspend fun getJoke(jokeId: String) =
            localDataSource.async.getJoke(jokeId)?.toDomain()

        override suspend fun getRandomJoke() =
            localDataSource.async.getRandomJoke()?.toDomain()

        override fun getFavoriteJokes() =
            localDataSource.observable.getFavoriteJokes().map { list ->
                list.map { joke -> joke.toDomain() }
            }
    }

    // random joke attempts remote and defaults to local cache
    override suspend fun getRandomJoke(): DomainJoke? = try {
        remote.getRandomJoke()
    } catch (e: Throwable) {
        local.getRandomJoke()
    }

    // get joke attempts local and defaults to remote source
    override suspend fun getJoke(jokeId: String): DomainJoke? = try {
        local.getJoke(jokeId)
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
    override suspend fun saveJoke(joke: DomainJoke) {
        local.saveJoke(joke)
    }
}
