package com.example.emapp.classes

import android.widget.EditText
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class PasswordValidator {

    private var messege: String = ""

    public fun ValidatePassword(edPassword: TextInputLayout):Boolean{
        if(edPassword.editText?.text.toString().isEmpty()){
            messege = "Fill fieald"
            return false
        }
        if((edPassword.editText?.text.toString().length<4) or (edPassword.editText?.text.toString().length>12)){
            messege = "Too short\nMust be from 4 to 12 symbols"
            return false
        }

        return true
    }

    public fun GetError():String{
        return messege
    }
}