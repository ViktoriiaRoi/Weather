package com.viktoriiaroi.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriiaroi.core.database.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather LIMIT 1")
    suspend fun getWeather(): WeatherEntity?

    @Query("SELECT city FROM weather LIMIT 1")
    suspend fun getCityName(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)
}