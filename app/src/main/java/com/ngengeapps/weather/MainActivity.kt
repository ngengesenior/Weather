package com.ngengeapps.weather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ngengeapps.adapters.HourlyController
import com.ngengeapps.models.CurrentWeather
import com.ngengeapps.models._4DaysHourlyForecast
import com.ngengeapps.weather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.current_weather_layout.*
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {
    private val AUTOCOMPLETE_REQUEST_CODE = 300
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var fields: List<Place.Field>
    private lateinit var observer: Observer<Response<CurrentWeather>>

    private lateinit var _4DaysObserver: Observer<Response<_4DaysHourlyForecast>>
    private lateinit var controller: HourlyController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottomBar)
        val intent = intent
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, BuildConfig.MAPS_API, Locale.US);
        }

        controller = HourlyController()
        hourlyRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hourlyRecyclerview.adapter = controller.adapter

        fields = listOf(Place.Field.NAME, Place.Field.LAT_LNG)

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        observer = Observer {
            if (it.isSuccessful) {
                it.body()?.let { currentWeather ->
                    updateWeather(currentWeather)

                }
            }


        }

        _4DaysObserver = Observer { weatherResponse ->
            if (weatherResponse.isSuccessful) {
                weatherResponse.body()?.let {
                    controller.setData(it)
                    Log.d("TAG--", "$it")
                }

                }

        }

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.search_action -> {
                val intent = Autocomplete.IntentBuilder(
                    AutocompleteActivityMode.FULLSCREEN, fields
                )
                    .build(this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)

                place.latLng?.let {
                    getWeatherByCoordinates(it)
                    //get4DaysWeather(it)
                    //updateWeather(it)

                } ?: place.name?.let {

                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStop() {
        super.onStop()


    }


    private fun getWeatherByCoordinates(latLng: LatLng) {

        weatherViewModel.getWeatherByCoordinates(latLng)
            .observe(this@MainActivity, observer)

    }


    private fun get4DaysWeather(latLng: LatLng) {
        weatherViewModel.get4DaysWeather(latLng)
            .observe(this, _4DaysObserver)
    }


    private fun updateWeather(currentWeather: CurrentWeather) {
        text_view_location.text = "${currentWeather.name}, ${currentWeather.sys.country}"
        text_view_temperature.text =
            applicationContext.getString(R.string.temperature, currentWeather.main.temperature)
        text_view_humidity.text =
            applicationContext.getString(R.string.humidity, currentWeather.main.humidity)
        text_view_condition.text = currentWeather.weather[0].description
        text_view_wind.text = applicationContext.getString(R.string.wind, currentWeather.wind.speed)
        text_view_temp_high.text =
            applicationContext.getString(R.string.high_temp, currentWeather.main.tempMax)
        text_view_temp_low.text =
            applicationContext.getString(R.string.low_temp, currentWeather.main.tempMin)
        text_view_barometer.text =
            applicationContext.getString(R.string.barometer, currentWeather.main.pressure)
        Picasso.get()
            .load("http://openweathermap.org/img/wn/${currentWeather.weather[0].icon}@2x.png")
            .into(image_view_condition)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentWeather.dateTime
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        text_view_last_updated.text = applicationContext.getString(R.string.update_as, hour, minute)


    }


    fun updateCurrentAnd4DaysResponse(latLng: LatLng) {


    }
}
