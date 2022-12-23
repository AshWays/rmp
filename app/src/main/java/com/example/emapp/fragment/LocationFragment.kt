package com.example.emapp.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.emapp.R
import com.example.emapp.view.MainActivity
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_account_user.*
import kotlinx.android.synthetic.main.fragment_map.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

private const val TAG = "LocationFragmrnt:"
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var gMap: GoogleMap
private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

class LocationFragment : Fragment(), OnMapReadyCallback, LocationListener {

    private var param1: String? = null
    private var param2: String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map_fragment.onCreate(savedInstanceState)
        map_fragment.onResume()

        map_fragment.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        CheckPermissions()

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    private fun CheckPermissions(){

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
                gMap.addMarker(MarkerOptions().position(LatLng(lat,lng)).title("Marker in Sydney"))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        if (requestCode == 1){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireActivity(), "Permission granted", Toast.LENGTH_LONG).show()
                    getLocation()
                }
                else {
                    Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_LONG).show()
                }
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
            gMap = googleMap

            val sydney = LatLng(-34.0, 151.0)
            gMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        }
    }

    override fun onLocationChanged(location: Location) {

    }



}