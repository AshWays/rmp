package com.example.emapp.classes

import android.widget.EditText
import androidx.core.view.isEmpty
import com.example.emapp.R
import com.example.emapp.view.CreateAccount
import com.example.emapp.view.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class PasswordValidatorMainActivity(view: MainActivity) {

    private var messege: String = ""
    private val view = view

    public fun ValidatePassword(edPassword: String):Boolean{
        if(edPassword.isEmpty()){
            messege = view.getString(R.string.fill_field)
            return false
        }
        if((edPassword.length<4) or (edPassword.length>12)){
            messege = view.getString(R.string.short_password)
            return false
        }

        return true
    }

    public fun GetError():String{
        return messege
    }
}

class PasswordValidatorCreateAccount(view: CreateAccount) {

    private var messege: String = ""
    private val view = view

    public fun ValidatePassword(edPassword: String):Boolean{
        if(edPassword.isEmpty()){
            messege = view.getString(R.string.fill_field)
            return false
        }
        if((edPassword.length<4) or (edPassword.length>12)){
            messege = view.getString(R.string.short_password)
            return false
        }

        return true
    }

    public fun GetError():String{
        return messege
    }
}