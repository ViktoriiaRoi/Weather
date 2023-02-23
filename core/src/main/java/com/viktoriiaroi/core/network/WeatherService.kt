package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.model.Units
import com.viktoriiaroi.core.network.dto.WeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun fetchWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: Units,
    ): Response<WeatherDTO>
}