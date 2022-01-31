package com.github.ryjen.jokeapp.meta.modules

import android.content.Context
import androidx.room.Room
import com.github.ryjen.jokeapp.data.storage.JokeDao
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.github.ryjen.jokeapp.domain.usecase.GetNetworkAvailability
import com.github.ryjen.jokeapp.domain.usecase.GetUserLocale
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideNetworkAvailability(@ApplicationContext context: Context): GetNetworkAvailability {
        return GetNetworkAvailability(context)
    }

    @Provides
    fun provideUserLocale(): GetUserLocale {
        return GetUserLocale()
    }
}
