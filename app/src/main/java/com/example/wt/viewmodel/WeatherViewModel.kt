package com.example.wt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _citiesStateFlow = MutableStateFlow(listOf<List<Weather>>())

    val citiesStateFlow: StateFlow<List<List<Weather>>>
        get() = _citiesStateFlow

    fun getWeatherList(cities: List<String>) {
        viewModelScope.launch {
            val map = cities.map {
                flowOf(weatherRepository.getWeather(it))
            }
            combine(map) { it }.flowOn(Dispatchers.IO).catch { println(it.message) }
                .map { if (it.first().status != "1") emptyArray<Response>() else it }
                .map { it.map { it.forecasts } }.collect {
                    println("response:$it")
                    _citiesStateFlow.value = it
                }
        }
    }

}

