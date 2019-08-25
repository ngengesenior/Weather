package com.ngengeapps.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("coord") val coordinates:Coordinates,
    @SerializedName("weather") val weather:List<Item>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("dt") val dateTime:Long,
    @SerializedName("name") val name: String,
    @SerializedName("sys") val sys: Sys


)

data class City(
    @SerializedName("name") val name: String,
    @SerializedName("coords") val coordinates: Coordinates,
    @SerializedName("country") val country: String
)

data class Coordinates(@SerializedName("lat") val latitude:Double,
                       @SerializedName("lon") val longitude:Double)

data class Wind(@SerializedName("speed") val speed:Double,
                @SerializedName("degree") val degree:Int)


data class Main(
    @SerializedName("temp") val temperature: Float,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Long,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
)


data class Sys(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class Item(
    @SerializedName("id") val id:Int,
    @SerializedName("main") val main:String,
    @SerializedName("description") val description:String,
    @SerializedName("icon") val icon:String
)



