package com.example.wt


const val WeatherKey = "abfe775b1fc195bb32428dfcc6e0907d"


val CityList = listOf<City>(
    City("北京", "110101"),
    City("上海", "310101"),
    City("深圳", "440303"),
    City("广州", "440103"),
    City("苏州", "320505"),
    City("沈阳", "210102"),
)

data class City(val name: String, val code: String)