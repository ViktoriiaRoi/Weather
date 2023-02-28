package com.viktoriiaroi.weather.ui.weather

import com.viktoriiaroi.core.model.Weather

data class WeatherUIState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val errorMessage: String? = null,
)