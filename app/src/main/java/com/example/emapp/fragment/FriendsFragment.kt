/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.emapp.R
import com.example.emapp.classes.User
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_friends.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

class FriendsFragment : BaseFragment() {

    private var param1: String? = null
    private var param2: String? = null
    private val database = Firebase.database
    private val myRef = database.getReference("Bdzils")
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

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

        return inflater.inflate(R.layout.fragment_friends, container, false)
    }


    @SuppressLint("MissingPermission")
    private fun getLocation(){

        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
            if (it == null){
                Toast.makeText(requireActivity(), "Can'y get location", Toast.LENGTH_LONG).show()
            }
            else it.apply {
                val lat = it.latitude.toString()
                val lng = it.longitude.toString()
                enterDataToBase(edMessege.text.toString(),edName.text.toString(),lat,lng)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSend.setOnClickListener {
            getLocation()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FriendsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun enterDataToBase(messege: String,name: String, lat: String, lng: String){
        val user:String = firebaseUser?.email.toString()
        val newUser = User(user,name,messege,lat,lng)
        myRef.push().setValue(newUser).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(context,"Yep", Toast.LENGTH_LONG).show()
                edName.setText("")
                edMessege.setText("")
            }
            else{
                Toast.makeText(context,"Oops", Toast.LENGTH_LONG).show()
                edName.setText("")
                edMessege.setText("")
            }
        }
    }
}