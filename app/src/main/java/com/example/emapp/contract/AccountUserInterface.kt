package com.example.emapp.contract

import android.widget.EditText

interface AccountUserInterface {
    interface View {
        fun initView()
    }

    interface Presenter {
        fun signOut()
    }

    interface Model {

    }

}