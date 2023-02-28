package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.WeatherDao
import com.viktoriiaroi.core.database.entity.WeatherEntity
import com.viktoriiaroi.core.model.Units
import com.viktoriiaroi.core.model.Weather
import com.viktoriiaroi.core.network.WeatherService
import com.viktoriiaroi.core.network.dto.WeatherDTO
import com.viktoriiaroi.core.network.exception.NetworkException
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
) {
    companion object {
        private const val API_KEY = "bb41453a8cdbb6487ad111cff38c9c3a"
    }

    suspend fun fetchWeather(city: String, units: Units): Pair<Weather?, Throwable?> =
        fetchWeatherFromNetwork(city, units).fold({
            insertWeatherToDatabase(it)
            Pair(it, null)
        }, {
            Pair(fetchWeatherFromDatabase(), it)
        })

    suspend fun fetchCityName(): String? = weatherDao.getCityName()

    private suspend fun fetchWeatherFromNetwork(city: String, units: Units): Result<Weather> =
        try {
            val response = weatherService.fetchWeather(city, API_KEY, units)
            processNetworkResponse(response)
        } catch (e: UnknownHostException) {
            Result.failure(NetworkException.NoInternet)
        } catch (e: Exception) {
            Result.failure(e)
        }

    private fun processNetworkResponse(response: Response<WeatherDTO>): Result<Weather> {
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            Result.success(Weather.fromDTO(body))
        } else {
            Result.failure(NetworkException.EmptyData)
        }
    }

    private suspend fun fetchWeatherFromDatabase(): Weather? =
        weatherDao.getWeather()?.let { Weather.fromEntity(it) }

    private suspend fun insertWeatherToDatabase(weather: Weather) {
        weatherDao.insertWeather(WeatherEntity.fromModel(weather))
    }
}