package com.example.emapp.classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val user:String?=null,
    val name:String?=null,
    val comment:String?=null,
    val lat:Long?=null,
    val lng:Long?=null
) {

}