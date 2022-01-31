package com.github.ryjen.jokeapp.meta.modules

import android.content.Context
import androidx.room.Room
import com.github.ryjen.jokeapp.data.storage.JokeDao
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
@Module
class FakeDatabaseModule {

    @Singleton
    @Provides
    fun provideFakeJokeDatabase(@ApplicationContext context: Context): JokeDatabase {
        return Room.inMemoryDatabaseBuilder(
            context, JokeDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideFakeJokeDao(db: JokeDatabase): JokeDao {
        return db.jokeDao()
    }
}
