package com.ngengeapps.weather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ngengeapps.models.CurrentWeather
import com.ngengeapps.weather.repository.WeatherRepository
import com.ngengeapps.weather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.current_weather_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {
    private val AUTOCOMPLETE_REQUEST_CODE = 300
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var fields:List<Place.Field>
    private lateinit var observer:Observer<MutableLiveData<CurrentWeather>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intent
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, BuildConfig.MAPS_API, Locale.US);
        }
        fields = listOf(Place.Field.NAME,Place.Field.LAT_LNG)
        observer = object : Observer<MutableLiveData<CurrentWeather>>, Observer<MutableLiveData<CurrentWeather>> {
            override fun onChanged(t: MutableLiveData<CurrentWeather>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        }
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        setSupportActionBar(bottomBar)

        if (intent.hasExtra("location")) {
            val latLng = intent.getSerializableExtra("location") as Pair<*, *>
            weatherViewModel.getWeatherByCoordinates(latLng.first as Double,latLng.second as Double)
                .observe(this,observer)
        }
        weatherViewModel.currentWeather.observe(this, Observer {
            it?.let{

            }

        })
        /*weatherViewModel.getWeatherByCity("Buea")
            .observe(this, Observer {
                it?.let {
                    textViewTemperature.text = "${it.} \u00B0"
                    textViewHumidity.text = "Humidity ${it.main.humidity}"
                    textViewCondition.text = "${it.weather[0].main}"
                    textViewWind.text = "Wind ${it.wind.speed}"
                    Picasso.get()
                        .load("http://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png")
                        .into(imageViewCondition)
                    
                }
            })*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.search_action ->{
                val intent = Autocomplete.IntentBuilder(
                    AutocompleteActivityMode.FULLSCREEN, fields)
                    .build(this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK)
            {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                /*Toast.makeText(this,"Lat:${place.latLng}",Toast.LENGTH_LONG).show()
                place.latLng?.let {
                    //getWeatherByCoordinates(it)

                }?:place.name?.let { getWeatherByCity(
                    it
                ) }*/

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStop() {
        super.onStop()

    }



    /*fun getWeatherByCoordinates(latLng:LatLng) {
        GlobalScope.launch(Dispatchers.Main) {
            val data  = repository.getWeatherByCityCoordinates(latLng.latitude,latLng.longitude)
            data?.let {
                textViewTemperature.text = "${it.main.temperature} \u00B0"
                textViewHumidity.text = "Humidity ${it.main.humidity}"
                textViewCondition.text = "${it.weather[0].main}"
                textViewWind.text = "Wind ${it.wind.speed}"
                Picasso.get()
                    .load("http://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png")
                    .into(imageViewCondition)
            }
        }
    }*/

}
