package com.viktoriiaroi.core.database.entity

import com.viktoriiaroi.core.model.Weather
import junit.framework.TestCase
import org.junit.Test

internal class WeatherEntityTest {
    @Test
    fun fromModel_SimpleWeather_CorrectConversion() {
        val weather = Weather(
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
        val weatherEntity = WeatherEntity.fromModel(weather)

        TestCase.assertEquals(0, weatherEntity.id)
        TestCase.assertEquals(2, weatherEntity.temp)
        TestCase.assertEquals("01d", weatherEntity.iconCode)
        TestCase.assertEquals("Clear sky", weatherEntity.description)
        TestCase.assertEquals(-3, weatherEntity.feelsLike)
        TestCase.assertEquals(1, weatherEntity.wind)
        TestCase.assertEquals(70, weatherEntity.humidity)
        TestCase.assertEquals("Lviv", weatherEntity.city)
        TestCase.assertEquals("UA", weatherEntity.country)
        TestCase.assertEquals(1646036400, weatherEntity.timestamp)
    }

    @Test
    fun fromModel_DefaultWeather_DefaultWeatherEntity() {
        val weather = Weather()
        val weatherEntity = WeatherEntity.fromModel(weather)

        TestCase.assertEquals(0, weatherEntity.id)
        TestCase.assertNull(weather.temp)
        TestCase.assertNull(weather.iconCode)
        TestCase.assertNull(weather.description)
        TestCase.assertNull(weather.feelsLike)
        TestCase.assertNull(weather.wind)
        TestCase.assertNull(weather.humidity)
        TestCase.assertNull(weather.city)
        TestCase.assertNull(weather.country)
        TestCase.assertNull(weather.timestamp)
    }
}
