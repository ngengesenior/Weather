package com.ngengeapps.weather.webservice

import com.ngengeapps.models.CurrentWeather
import com.ngengeapps.models._4DaysHourlyForecast
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {


    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") city: String, @Query("APPID") appID: String, @Query(
            "units"
        ) units: String
    ): Response<CurrentWeather>

    @GET("weather")
    suspend fun getWeatherByCoordinates(@Query("lat") latitude:Double,
                                        @Query("lon") longitude:Double,
                                        @Query("APPID") appID: String, @Query("units") units: String
    ): Response<CurrentWeather>

    @GET("forecast")
    suspend fun get4DaysHourlyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") appID: String
    ): Response<_4DaysHourlyForecast>


    companion object {
        val BASE_URL = "http://api.openweathermap.org/data/2.5/"

        fun create(httpUrl: HttpUrl): WebService {
            val okHttpClient = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .client(okHttpClient)
                .build()
                .create(WebService::class.java)
        }


        fun create(): WebService= create(HttpUrl.parse(BASE_URL)!!)
    }
}