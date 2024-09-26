/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.fragment

import android.Manifest
import android.R.attr.defaultValue
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.emapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_account_user.*
import kotlinx.android.synthetic.main.fragment_map.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var gMap: GoogleMap
private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

class LocationFragment : BaseFragment(), OnMapReadyCallback, LocationListener {

    private var glat: Double = 0.0
    private var glng: Double = 0.0

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            glat = bundle.getDouble("lat")
            glng = bundle.getDouble("lng")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map_fragment.onCreate(savedInstanceState)
        map_fragment.onResume()
        map_fragment.getMapAsync(this)

        CheckPermissions()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        CheckPermissions()

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun CheckPermissions(){

        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

        } else {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){

        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
            if (it == null){
                Toast.makeText(requireActivity(), "Can't get location", Toast.LENGTH_LONG).show()
            }
            else it.apply {
                val lat = it.latitude
                val lng = it.longitude
                val place = LatLng(lat, lng)
                if ((glat != 0.0) and (glng != 0.0)){
                    gMap.addMarker(MarkerOptions().position(LatLng(glat, glng)).title("mark"))
                }
                gMap.addMarker(MarkerOptions().position(place).title("you"))
                gMap.moveCamera(CameraUpdateFactory.newLatLng(place))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LocationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.let {
            gMap = it

        }
    }

    override fun onLocationChanged(location: Location) {

    }



}