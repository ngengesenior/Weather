package com.ngengeapps.models

import com.google.gson.annotations.SerializedName

data class HourlyForecast(
    @SerializedName("dt") val dt: Long,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Item>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("dt_tx") val dtTx: String
)


data class _4DaysHourlyForecast(
    @SerializedName("list") val list: List<HourlyForecast> = listOf(),
    @SerializedName("city") val city: City,
    @SerializedName("code") val code: String

)