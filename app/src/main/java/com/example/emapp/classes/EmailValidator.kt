package com.example.emapp.classes


import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.IgnoreExtraProperties
import com.example.emapp.R


@IgnoreExtraProperties
class EmailValidator {

    private var messege: String = ""



    public fun ValidateEmail(edEmail: TextInputLayout):Boolean{
        if(edEmail.editText?.text.toString().isEmpty()){
            messege = "Fill field"
            return false
        }
        if((!(edEmail.editText?.text.toString().contains("@gmail.com")))&&
            (!(edEmail.editText?.text.toString().contains("@mail.ru")))&&
            (!(edEmail.editText?.text.toString().contains("@yandex.ru")))){
            messege = "It's not email adress\nAcceptable Emails:@gmail.com, @mail.ru, @yandex.ru"
            return false
        }
        return true
    }

    public fun GetError():String{
        return messege
    }

}