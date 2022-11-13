package com.example.emapp.presenter

import android.widget.EditText
import com.example.emapp.contract.AccountUserInterface.*
import com.example.emapp.model.AccountUserModel
import com.example.emapp.view.AccountUser

class AccountUserPresenter(_view: AccountUser): Presenter {

    private var view: AccountUser = _view
    private var model: Model = AccountUserModel()


    init {
        view.initView()
    }

    override fun signOut() {

    }

}