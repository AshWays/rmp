package com.example.emapp.classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val user:String="null",
    val name:String="null",
    val comment:String="null",
    val lat:String ="0",
    val lng:String ="0"
) {

}