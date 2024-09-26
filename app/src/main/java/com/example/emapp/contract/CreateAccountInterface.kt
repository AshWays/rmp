/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.contract

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

interface CreateAccountInterface {

    interface View {
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