/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(val id:String?=null, val messege:String?=null) {

}