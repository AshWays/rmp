package com.example.emapp.model

import com.example.emapp.classes.User
import com.example.emapp.contract.ContractInterface.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivityModel: Model {

    private val database = Firebase.database
    private val myRef = database.getReference("Users")



    override fun enterDataToBase(edEmail: String, edPassword: String){
        val id:String = myRef.key.toString()
        val newUser = User(id,edEmail,edPassword)
        myRef.push().setValue(newUser)

    }
}