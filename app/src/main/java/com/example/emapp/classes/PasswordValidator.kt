/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.classes

import android.content.res.Resources
import android.widget.EditText
import androidx.core.view.isEmpty
import com.example.emapp.R
import com.example.emapp.view.CreateAccount
import com.example.emapp.view.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.IgnoreExtraProperties

class PasswordValidator() {

    public fun ValidatePassword(edPassword: String):Boolean{
        if(edPassword.isEmpty()){
            return false
        }
        if((edPassword.length<4) or (edPassword.length>12)){
            return false
        }

        return true
    }

}
