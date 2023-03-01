package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.entity.WeatherEntity
import com.viktoriiaroi.core.network.dto.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

internal class WeatherTest {
    @Test
    fun fromDTO_SimpleWeatherDTO_CorrectConversion() {
        val weatherDTO = WeatherDTO(
            conditions = listOf(ConditionDTO(description = "clear sky", icon = "01d")),
            main = MainDTO(temp = 1.58, feelsLike = -3.21, humidity = 70),
            wind = WindDTO(speed = 0.8),
            sys = SysDTO(country = "UA"),
            name = "Lviv",
            dt = 1646036400 // Feb 28, 2022 12:00:00 AM GMT
        )
        val weather = Weather.fromDTO(weatherDTO)

        assertEquals(2, weather.temp)
        assertEquals("01d", weather.iconCode)
        assertEquals("Clear sky", weather.description)
        assertEquals(-3, weather.feelsLike)
        assertEquals(1, weather.wind)
        assertEquals(70, weather.humidity)
        assertEquals("Lviv", weather.city)
        assertEquals("UA", weather.country)
        assertEquals(1646036400, weather.timestamp)
    }

    @Test
    fun fromDTO_DefaultWeatherDTO_DefaultWeather() {
        val weatherDTO = WeatherDTO()
        val weather = Weather.fromDTO(weatherDTO)

        assertNull(weather.temp)
        assertNull(weather.iconCode)
        assertNull(weather.description)
        assertNull(weather.feelsLike)
        assertNull(weather.wind)
        assertNull(weather.humidity)
        assertNull(weather.city)
        assertNull(weather.country)
        assertNull(weather.timestamp)
    }

    @Test
    fun fromEntity_SimpleWeatherEntity_CorrectConversion() {
        val weatherEntity = WeatherEntity(
            id = 0,
            temp = 2,
            iconCode = "01d",
            description = "Clear sky",
            feelsLike = -3,
            wind = 1,
            humidity = 70,
            city = "Lviv",
            country = "UA",
            timestamp = 1646036400 // Feb 28, 2022 12:00:00 AM GMT
        )
        val weather = Weather.fromEntity(weatherEntity)

        assertEquals(2, weather.temp)
        assertEquals("01d", weather.iconCode)
        assertEquals("Clear sky", weather.description)
        assertEquals(-3, weather.feelsLike)
        assertEquals(1, weather.wind)
        assertEquals(70, weather.humidity)
        assertEquals("Lviv", weather.city)
        assertEquals("UA", weather.country)
        assertEquals(1646036400, weather.timestamp)
    }

    @Test
    fun fromEntity_DefaultWeatherEntity_DefaultWeather() {
        val weatherEntity = WeatherEntity(id = 0)
        val weather = Weather.fromEntity(weatherEntity)

        assertNull(weather.temp)
        assertNull(weather.iconCode)
        assertNull(weather.description)
        assertNull(weather.feelsLike)
        assertNull(weather.wind)
        assertNull(weather.humidity)
        assertNull(weather.city)
        assertNull(weather.country)
        assertNull(weather.timestamp)
    }
}