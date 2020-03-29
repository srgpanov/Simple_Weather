package com.srgpanov.simpleweather.data

import WeatherRequest
import com.srgpanov.simpleweather.other.ResponseResult
import java.io.IOException

class RemoteDataSourceImpl:RemoteDataSource {
    private val remoteService = RetrofitClient.create()
    suspend fun getWeather(): ResponseResult<out Any> {
        try {
            val response = remoteService.getWeather(45.035470, 38.975313)
            return (if (response.isSuccessful) {
                ResponseResult.Success(response.body())
            } else {
                ResponseResult.Error(
                    IOException("Error on loading weather"),
                    response.code()
                )
            })
        } catch (e: Exception) {
            return ResponseResult.Error(e)
        }
    }

    override suspend fun getWeather(
        lat: Double,
        lon: Double,
        lang: String,
        day: Int,
        hours: Boolean,
        extra: Boolean
    ): WeatherRequest? {
        try {
            val response =remoteService
                .getWeather(lat, lon, lang, day, hours, extra)
            return when (response.isSuccessful){
                true -> response.body()
                false -> null
            }
        }catch (e:Exception){
            return null
        }


    }


}