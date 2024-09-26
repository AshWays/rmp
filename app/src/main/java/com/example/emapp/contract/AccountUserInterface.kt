/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.contract

import android.widget.EditText
import androidx.fragment.app.Fragment

interface AccountUserInterface {
    interface View {
        fun replaceFragment(fragment: Fragment)
        fun logOut()
    }

    interface Presenter {
        fun signOut()
    }

    interface Model {

    }

}