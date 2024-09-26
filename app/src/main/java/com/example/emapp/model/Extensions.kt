/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.model

import android.app.Activity
import android.widget.Toast

object Extensions {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}