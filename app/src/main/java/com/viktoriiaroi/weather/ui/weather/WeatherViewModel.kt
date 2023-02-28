package com.viktoriiaroi.weather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.model.Units
import com.viktoriiaroi.core.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherUIState())
    val weatherState: StateFlow<WeatherUIState> = _weatherState

    private val cityFromState
        get() = weatherState.value.weather?.city

    init {
        fetchWeather()
    }

    fun fetchWeather(city: String? = null) {
        if (_weatherState.value.isLoading) return
        viewModelScope.launch(Dispatchers.Default) {
            _weatherState.update { it.copy(isLoading = true) }
            val cityQuery = city ?: cityFromState ?: fetchCityFromDatabase()
            if (cityQuery != null) {
                val resultPair = repository.fetchWeather(cityQuery, Units.metric)
                _weatherState.update {
                    it.copy(isLoading = false,
                        weather = resultPair.first,
                        errorMessage = resultPair.second?.message)
                }
            } else {
                _weatherState.update {
                    it.copy(isLoading = false,
                        weather = null,
                        errorMessage = null)
                }
            }
        }
    }

    private suspend fun fetchCityFromDatabase() = repository.fetchCityName()

    fun onErrorShown() {
        _weatherState.update {
            it.copy(errorMessage = null)
        }
    }
}
