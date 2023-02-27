package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.entity.WeatherEntity
import com.viktoriiaroi.core.network.dto.WeatherDTO
import kotlin.math.roundToInt

class Weather(
    val temp: Int? = null,
    val iconCode: String? = null,
    val description: String? = null,
    val feelsLike: Int? = null,
    val wind: Int? = null,
    val humidity: Int? = null,
    val city: String,
    val country: String,
    val timestamp: Int,
) {
    companion object {
        fun fromDTO(src: WeatherDTO) = Weather(
            temp = src.main?.temp?.roundToInt(),
            iconCode = src.conditions.firstOrNull()?.icon,
            description = src.conditions.firstOrNull()?.description?.replaceFirstChar(Char::titlecase),
            feelsLike = src.main?.feelsLike?.roundToInt(),
            wind = src.wind?.speed?.roundToInt(),
            humidity = src.main?.humidity,
            city = src.name ?: "",
            country = src.sys?.country ?: "",
            timestamp = src.dt ?: 0
        )

        fun fromEntity(src: WeatherEntity) = Weather(
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