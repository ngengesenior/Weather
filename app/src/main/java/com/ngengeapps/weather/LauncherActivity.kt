package com.ngengeapps.weather

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.Places




class LauncherActivity : AppCompatActivity() {

    private lateinit var latLng:Pair<Double,Double>

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        val intent = Intent(this,MainActivity::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            latLng = Pair(it.latitude,it.longitude)

            intent.putExtra("location",latLng)
            startActivity(intent)

        }.addOnFailureListener {

            latLng = Pair(4.1560,9.2632)
            intent.putExtra("location",latLng)
            startActivity(intent)
        }



    }
}
