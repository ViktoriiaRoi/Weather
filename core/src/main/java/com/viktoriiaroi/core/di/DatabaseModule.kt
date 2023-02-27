package com.viktoriiaroi.core.di

import android.content.Context
import androidx.room.Room
import com.viktoriiaroi.core.database.WeatherDatabase
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
        private const val DB_NAME = "weather.db"
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesWeatherDao(database: WeatherDatabase) = database.getWeatherDao()
}