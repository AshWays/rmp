package com.example.emapp.classes


import com.example.emapp.R
import com.example.emapp.view.CreateAccount
import com.example.emapp.view.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.IgnoreExtraProperties



@IgnoreExtraProperties
class EmailValidatorMainActivity(view: MainActivity) {

    private var messege: String = ""
    private val view = view


    public fun ValidateEmail(edEmail: String):Boolean{
        if(edEmail.isEmpty()){
            messege = view.getString(R.string.fill_field)
            return false
        }
        if((!(edEmail.contains("@gmail.com")))&&
            (!(edEmail.contains("@mail.ru")))&&
            (!(edEmail.contains("@yandex.ru")))){
            messege = view.getString(R.string.invalid_email)
            return false
        }
        return true
    }

    public fun GetError():String{
        return messege
    }

}

class EmailValidatorCreateAccount(view: CreateAccount) {

    private var messege: String = ""
    private val view = view


    public fun ValidateEmail(edEmail: String):Boolean{
        if(edEmail.isEmpty()){
            messege = view.getString(R.string.fill_field)
            return false
        }
        if((!(edEmail.contains("@gmail.com")))&&
            (!(edEmail.contains("@mail.ru")))&&
            (!(edEmail.contains("@yandex.ru")))){
            messege = view.getString(R.string.invalid_email)
            return false
        }
        return true
    }

    public fun GetError():String{
        return messege
    }

}