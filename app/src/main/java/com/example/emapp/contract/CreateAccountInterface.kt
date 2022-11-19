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
        fun createAccount(edCreateEmail: TextInputLayout, edCreatePassword: TextInputLayout,edConfirmPassword: TextInputLayout)
        fun validateEmail(edEmail: TextInputLayout)
        fun validatePassword(edPassword: TextInputLayout)
    }

    interface Model {
        fun getFirebaseAuth():FirebaseAuth
    }

}