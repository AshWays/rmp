/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.presenter

import android.widget.EditText
import com.example.emapp.contract.AccountUserInterface.*
import com.example.emapp.model.AccountUserModel
import com.example.emapp.view.AccountUser

class AccountUserPresenter(_view: AccountUser): Presenter {

    private var view: AccountUser = _view
    private var model: Model = AccountUserModel()


    override fun signOut() {

    }

}