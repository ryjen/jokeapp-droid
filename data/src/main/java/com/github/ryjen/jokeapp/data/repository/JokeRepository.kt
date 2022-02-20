package com.github.ryjen.jokeapp.data.repository

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
) : DomainRepository, ObservableJokeRepository, AsyncJokeRepository, SyncJokeRepository {

    override val async: AsyncJokeRepository = this

    override val observable: ObservableJokeRepository = this

    override val sync: SyncJokeRepository = this

    override val remote: RemoteJokeRepository = object : RemoteJokeRepository {
        override suspend fun getJoke(jokeId: String) = remoteDataSource.getJoke(jokeId)
        override suspend fun getRandomJoke() = remoteDataSource.randomJoke()
    }

    override val local: LocalJokeRepository = object : LocalJokeRepository {
        override suspend fun addFavorite(joke: DomainJoke) =
            localDataSource.addFavorite(joke.toData())

        override suspend fun removeFavorite(joke: DomainJoke) =
            localDataSource.removeFavorite(joke.toData())

        override suspend fun saveJoke(joke: DomainJoke) = localDataSource.cacheJoke(joke.toData())

        override suspend fun getJoke(jokeId: String) = localDataSource.getJoke(jokeId)?.toDomain()

        override suspend fun getRandomJoke() = localDataSource.randomJoke()?.toDomain()

        override fun getFavoriteJokes() = localDataSource.getFavoriteJokes().map { list ->
            list.map { joke -> joke.toDomain() }
        }
    }

    override fun getFavoriteJokes(): Flow<List<DomainJoke>> =
        local.getFavoriteJokes()

    override suspend fun getRandomJoke(): DomainJoke? = try {
        remote.getRandomJoke()
    } catch (e: Throwable) {
        local.getRandomJoke()
    }

    override suspend fun getJoke(jokeId: String): DomainJoke? = try {
        local.getJoke(jokeId)
    } catch (e: Throwable) {
        Timber.e("error fetching local joke", e)
        remote.getJoke(jokeId)
    }

    override suspend fun addFavorite(joke: DomainJoke) = try {
        local.addFavorite(joke)
        // add to remote service
    } catch (e: Throwable) {
        Timber.e("error adding joke", e)
        throw e
    }

    override suspend fun removeFavorite(joke: DomainJoke) = try {
        local.removeFavorite(joke)
        // remove from remote service
    } catch (e: Throwable) {
        Timber.e("error removing joke", e)
        throw e
    }

    override suspend fun saveJoke(joke: DomainJoke) {
        local.saveJoke(joke)
    }

}
