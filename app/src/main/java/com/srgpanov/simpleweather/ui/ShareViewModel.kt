package com.srgpanov.simpleweather.ui

import WeatherRequest
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel: ViewModel(){
    val request: MutableLiveData<WeatherRequest> =MutableLiveData<WeatherRequest>()

}