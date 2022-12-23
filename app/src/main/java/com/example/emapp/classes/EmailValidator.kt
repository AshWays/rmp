package com.example.emapp.classes


import android.content.res.Resources
import com.example.emapp.R
import com.example.emapp.view.CreateAccount
import com.example.emapp.view.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.IgnoreExtraProperties


class EmailValidator() {

    public fun ValidateEmail(edEmail: String):Boolean{
        if(edEmail.isEmpty()){
            return false
        }
        if((!(edEmail.contains("@gmail.com")))&&
            (!(edEmail.contains("@mail.ru")))&&
            (!(edEmail.contains("@yandex.ru")))){
            return false
        }
        return true
    }

}
