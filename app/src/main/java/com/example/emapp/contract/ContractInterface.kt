package com.example.emapp.contract

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

interface ContractInterface {

    interface View {
        fun initView()
        fun signInIntent()
        fun toastWrong()

    }

    interface Presenter {
        fun enterData(edEmail: String, edPassword: String)
        fun validateEmail(edEmail: String)
        fun validatePassword(edPassword: String)
    }

    interface Model {
        fun enterDataToBase(edEmail: String, edPassword: String)
    }

}