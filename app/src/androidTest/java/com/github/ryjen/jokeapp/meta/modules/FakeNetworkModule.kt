package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.data.api.FakeJokeService
import com.github.ryjen.jokeapp.data.api.JokeService
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
