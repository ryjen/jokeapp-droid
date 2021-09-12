package com.atimi.jokeapp.modules

import com.atimi.jokeapp.service.FakeJokeService
import com.atimi.jokeapp.service.JokeService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
@Module
class FakeNetworkModule {

    @Singleton
    @Provides
    fun provideFakeJokeServer(): FakeJokeService {
        return FakeJokeService()
    }

    @Singleton
    @Provides
    fun provideFakeJokeClient(service: FakeJokeService): JokeService {
        return service.client()
    }
}