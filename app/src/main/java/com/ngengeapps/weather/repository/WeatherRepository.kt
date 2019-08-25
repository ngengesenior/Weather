package com.ngengeapps.weather.repository

import com.ngengeapps.weather.BuildConfig
import com.ngengeapps.weather.webservice.WebService

class WeatherRepository {
    val client = WebService.create()

    /*suspend fun getWeatherByCityName(city:String) = client.getWeatherByCityName(city,
        BuildConfig.WEATHER_API_KEY)*/

    suspend fun getWeatherByCityCoordinates(latitude:Double,longitude:Double) = client.getWeatherByCoordinates(latitude,
        longitude, BuildConfig.WEATHER_API_KEY, "metric"
    )

    suspend fun getCurrentWeatherByCityName(city: String) =
        client.getWeatherByCityName(city, BuildConfig.WEATHER_API_KEY, "metric")

    suspend fun get4DaysForecast(latitude: Double, longitude: Double) =
        client.get4DaysHourlyForecast(latitude, longitude, BuildConfig.WEATHER_API_KEY)


}
