package com.viktoriiaroi.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viktoriiaroi.core.model.Weather

@Entity(tableName = "weather")
class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "temp")
    val temp: Int? = null,
    @ColumnInfo(name = "image")
    val iconCode: String? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "feels_like")
    val feelsLike: Int? = null,
    @ColumnInfo(name = "wind")
    val wind: Int? = null,
    @ColumnInfo(name = "humidity")
    val humidity: Int? = null,
    @ColumnInfo(name = "city")
    val city: String? = null,
    @ColumnInfo(name = "country")
    val country: String? = null,
    @ColumnInfo(name = "timestamp")
    val timestamp: Int? = null,
) {
    companion object {
        fun fromModel(src: Weather) = WeatherEntity(
            id = 0,
            temp = src.temp,
            iconCode = src.iconCode,
            description = src.description,
            feelsLike = src.feelsLike,
            wind = src.wind,
            humidity = src.humidity,
            city = src.city,
            country = src.country,
            timestamp = src.timestamp
        )
    }
}