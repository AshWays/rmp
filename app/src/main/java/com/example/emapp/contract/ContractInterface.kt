package com.example.emapp.contract

import android.widget.EditText

interface ContractInterface {

    interface View {
        fun initView()
        fun successMessege()
        fun errorMessege()
        fun signInIntent()

    }

    interface Presenter {
        fun enterData(edEmail: EditText, edPassword: EditText)
    }

    interface Model {
        fun enterDataToBase(edEmail: String, edPassword: String)
    }

}