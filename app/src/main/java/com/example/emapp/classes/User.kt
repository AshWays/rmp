package com.example.emapp.classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val id:String?=null,val email:String?=null,val password:String?=null) {

}