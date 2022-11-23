package com.example.emapp.contract

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

interface CreateAccountInterface {

    interface View {
        fun initView()
        fun signInIntent()
        fun toastWrong()

    }

    interface Presenter {
        fun createAccount(edCreateEmail: String, edCreatePassword: String,edConfirmPassword: String)
        fun validateEmail(edEmail: String)
        fun validatePassword(edPassword: String)
    }

    interface Model {
        fun getFirebaseAuth():FirebaseAuth
    }

}