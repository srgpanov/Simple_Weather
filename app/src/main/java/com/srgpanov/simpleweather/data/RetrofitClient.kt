package com.srgpanov.simpleweather.data

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.srgpanov.simpleweather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl = "https://api.weather.yandex.ru/v1/"
    private fun getInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor=HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d("Retrofit",message)
            }
        });
        if (BuildConfig.DEBUG){
            loggingInterceptor.apply { level= HttpLoggingInterceptor.Level.BODY}
        }
        return loggingInterceptor;
    }


    private val httpClient = OkHttpClient.Builder()
        .addInterceptor {
            val original = it.request()
            val request = original.newBuilder()
                .addHeader("X-Yandex-API-Key", "098f7b10-efc0-48af-8fbe-a8f62507cb99")
                .build()
            it.proceed(request)
        }
        .addInterceptor(getInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun create(): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }
}