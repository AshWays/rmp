package com.example.emapp.model

import com.example.emapp.contract.CreateAccountInterface.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateAccountModel: Model {

    private val database = Firebase.database
    private val myRef = database.getReference("Users")
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser


    override fun getFirebaseAuth(): FirebaseAuth{

        return firebaseAuth

    }
}