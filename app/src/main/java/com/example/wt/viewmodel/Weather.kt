package com.example.wt.viewmodel

import java.util.*

data class Response(
    val status: String,
    val count: String,
    val info: String,
    val infocode: String,
    val forecasts: List<Weather>
)

data class Weather(
    val province: String,
    val city: String,
    val adcode: String,
    val weather: String,
    val temperature: String,
    val winddirection: String,
    val windpower: String,
    val humidity: String,
    val reporttime: String,
    val temperature_float: String,
    val humidity_float: String,
    val casts: List<Cast> = listOf()
)

data class Cast(
    val data: String,
    val week: String,
    val dayweather: String,
    val nightweather: String,
    val daytemp: String,
    val nighttemp: String,
    val daywind: String,
    val nightwind: String,
    val daypower: String,
    val nightpower: String,
)