/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val user:String="null",
    val name:String="null",
    val comment:String="null",
    val lat:String ="0",
    val lng:String ="0"
) {

}