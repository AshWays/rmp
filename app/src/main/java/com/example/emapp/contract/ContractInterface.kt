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
        fun enterData(edEmail: TextInputLayout, edPassword: TextInputLayout)
        fun validateEmail(edEmail: TextInputLayout)
        fun validatePassword(edPassword: TextInputLayout)
    }

    interface Model {
        fun enterDataToBase(edEmail: String, edPassword: String)
    }

}