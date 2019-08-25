package com.ngengeapps.epoxy_views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.google.android.material.card.MaterialCardView
import com.ngengeapps.weather.R
import com.squareup.picasso.Picasso

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class WeatherHourItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val temperatureTextView: TextView
    private val rootCardView: MaterialCardView
    private val conditionImageView: ImageView
    private val temperatureMin: TextView
    private val temperatureMax: TextView

    init {
        inflate(context, R.layout.item_weather_hour, this)
        rootCardView = findViewById(R.id.card_view_weather)
        conditionImageView = findViewById(R.id.image_view_condition)
        temperatureMax = findViewById(R.id.text_view_temp_max)
        temperatureMin = findViewById(R.id.text_view_temp_min)
        temperatureTextView = findViewById(R.id.text_view_temperature)

    }


    @ModelProp
    fun setWeatherImage(code: String) {

        Picasso.get()
            .load("http://openweathermap.org/img/wn/${code}@2x.png")
            .into(conditionImageView)

    }

    /* @ModelProp
     fun setLocation(location:String) {
         locationTextView.text = location
     }
 */
    @ModelProp
    fun setTemperature(temperature: String) {
        temperatureTextView.text = temperature
    }

    @ModelProp
    fun setMinTemperature(minTemp: String) {
        temperatureMin.text = minTemp
    }


    @ModelProp
    fun setMaxTemperature(maxTemp: String) {
        temperatureMax.text = maxTemp
    }

    @CallbackProp
    fun setItemClickListener(listener: OnClickListener?) {
        rootCardView.setOnClickListener(listener)
    }


}

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class Heading @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.text_view_header, this)
    }
}