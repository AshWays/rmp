package com.example.emapp.presenter

import android.content.Intent
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity
import com.example.emapp.model.CreateAccountModel
import com.example.emapp.contract.CreateAccountInterface.*
import com.example.emapp.model.FirebaseUtils.firebaseAuth
import com.example.emapp.view.CreateAccount

class CreateAccountPresenter(_view: CreateAccount): Presenter {

    private var view: CreateAccount = _view
    private var model: Model = CreateAccountModel()


    init {
        view.initView()
    }

    override fun createAccount(edCreateEmail: EditText, edCreatePassword: EditText, edConfirmPassword: EditText){
        if((edConfirmPassword.text.toString())==(edCreatePassword.text.toString())) {
            if ((edConfirmPassword.text.isNotEmpty()) and
                (edCreatePassword.text.isNotEmpty()) and
                (edCreateEmail.text.isNotEmpty())) {
                model.createDataBaseAccount(
                    edCreateEmail.text.toString(),
                    edCreatePassword.text.toString()
                )
                view.successMessege()
                view.signInIntent()
            }
            else view.errorMessege()

        }
        else view.notIdenticMessege()
    }

}