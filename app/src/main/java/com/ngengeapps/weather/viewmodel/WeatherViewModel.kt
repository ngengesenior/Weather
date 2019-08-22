package com.ngengeapps.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ngengeapps.models.CurrentWeather
import com.ngengeapps.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class WeatherViewModel: ViewModel() {
    private val repository = WeatherRepository()

    private var  _currentWeather = MutableLiveData<CurrentWeather?>()
    val currentWeather: LiveData<CurrentWeather?>
     get() = _currentWeather


    fun getWeatherByCoordinates(latitude:Double,longitude:Double) = liveData(Dispatchers.IO) {
        _currentWeather.value = repository.getWeatherByCityCoordinates(latitude,longitude)
        emit(_currentWeather)
    }



}