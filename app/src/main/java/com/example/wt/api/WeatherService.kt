package com.example.wt.api

import com.example.wt.viewmodel.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("weather/weatherInfo")
    suspend fun getWeather(
        @Query("city") city: String,
        @Query("key") ket: String = "",
        @Query("extensions") extension: String
    ): Response
}