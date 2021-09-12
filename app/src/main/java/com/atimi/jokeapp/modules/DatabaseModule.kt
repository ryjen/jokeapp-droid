package com.atimi.jokeapp.modules

import android.content.Context
import androidx.room.Room
import com.atimi.jokeapp.storage.JokeDao
import com.atimi.jokeapp.storage.JokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    companion object {
        private const val NAME = "jokeapp-db"
    }

    @Singleton
    @Provides
    fun provideJokeDatabase(@ApplicationContext context: Context): JokeDatabase {
        return Room.databaseBuilder(context, JokeDatabase::class.java, NAME)
            .build()
    }

    @Provides
    fun provideJokeDao(db: JokeDatabase): JokeDao {
        return db.jokeDao()
    }
}