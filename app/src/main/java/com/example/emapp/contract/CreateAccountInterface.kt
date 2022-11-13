package com.example.emapp.contract

import android.widget.EditText

interface CreateAccountInterface {

    interface View {
        fun initView()
        fun successMessege()
        fun errorMessege()
        fun notIdenticMessege()
        fun signInIntent()

    }

    interface Presenter {
        fun createAccount(edCreateEmail: EditText, edCreatePassword: EditText,edConfirmPassword: EditText)
    }

    interface Model {
        fun createDataBaseAccount(edCreateEmail: String, edCreatePassword: String)
    }

}