package com.example.wt.viewmodel

import com.example.wt.WeatherKey
import com.example.wt.api.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRepository {

    private val retrofit =
        Retrofit.Builder().baseUrl("https://restapi.amap.com/v3/")
            .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val service = retrofit.create(WeatherService::class.java)

    suspend fun getWeather(city: String, extension: String = "all"): Response {
        return service.getWeather(city, WeatherKey, extension)
    }
}