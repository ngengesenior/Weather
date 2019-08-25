package com.ngengeapps.weather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.gms.maps.model.LatLng
import com.ngengeapps.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class WeatherViewModel: ViewModel() {
    private val repository = WeatherRepository()


    fun getWeatherByCoordinates(latLng: LatLng) = liveData(Dispatchers.IO) {
        val currentWeather =
            repository.getWeatherByCityCoordinates(latLng.latitude, latLng.longitude)
        emit(currentWeather)
        Log.d("TAG--", "${currentWeather.body()}")
    }


    fun getWeatherByCity(city: String) = liveData(Dispatchers.IO) {
        val currentWeather = repository.getCurrentWeatherByCityName(city)
        emit(currentWeather)
    }

    fun get4DaysWeather(latLng: LatLng) = liveData(Dispatchers.IO) {
        val _4days = repository.get4DaysForecast(latLng.latitude, latLng.longitude)
        emit(_4days)
    }



}


