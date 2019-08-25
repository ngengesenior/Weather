package com.ngengeapps.adapters

import com.airbnb.epoxy.TypedEpoxyController
import com.ngengeapps.epoxy_views.WeatherHourItemViewModel_
import com.ngengeapps.models._4DaysHourlyForecast

class HourlyController : TypedEpoxyController<_4DaysHourlyForecast>() {

    private val hourlyModels = mutableListOf<WeatherHourItemViewModel_>()
    override fun buildModels(data: _4DaysHourlyForecast?) {
        data?.list?.forEachIndexed { index, hourlyForecast ->

            WeatherHourItemViewModel_()
                .id(index)
                .temperature("${hourlyForecast.main.temperature}")
                .minTemperature("${hourlyForecast.main.tempMin} \u00B0")
                .maxTemperature("${hourlyForecast.main.tempMin} \u00B0")
                .weatherImage(hourlyForecast.weather[0].icon)
                .addTo(this)

            /*hourlyModels.add(
                WeatherHourItemViewModel_()
                    .id(index)
                    .temperature("${hourlyForecast.main.temperature}")
                    .minTemperature("${hourlyForecast.main.tempMin} \u00B0")
                    .maxTemperature("${hourlyForecast.main.tempMin} \u00B0")
                    .weatherImage(hourlyForecast.weather[0].icon)
            )*/

        }


    }


}