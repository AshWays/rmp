package com.example.emapp.classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(val id:String?=null, val messege:String?=null) {

}