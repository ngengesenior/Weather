package com.ngengeapps.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("coord") val coordinates:Coordinates,
    @SerializedName("weather") val weather:List<Item>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("dt") val dateTime:Long,
    @SerializedName("name") val name:String

)

data class Coordinates(@SerializedName("lat") val latitude:Double,
                       @SerializedName("lon") val longitude:Double)

data class Wind(@SerializedName("speed") val speed:Double,
                @SerializedName("degree") val degree:Int)


data class Main(@SerializedName("temp") val temperature:Double,
                @SerializedName("pressure") val pressure:Long,
                @SerializedName("humidity") val humidity:Long)



data class Item(
    @SerializedName("id") val id:Int,
    @SerializedName("main") val main:String,
    @SerializedName("description") val description:String,
    @SerializedName("icon") val icon:String
)



