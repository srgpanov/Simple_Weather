package com.srgpanov.simpleweather.data

import WeatherRequest

interface RemoteDataSource {
    suspend fun getWeather(
        lat: Double,
        lon: Double,
        lang: String = "ru_RU",
        day: Int = 7,
        hours: Boolean = true,
        extra: Boolean = true
    ):WeatherRequest?
}