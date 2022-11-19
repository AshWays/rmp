package com.example.emapp.presenter

import com.example.emapp.classes.EmailValidator
import com.example.emapp.classes.PasswordValidator
import com.example.emapp.model.MainActivityModel
import com.example.emapp.contract.ContractInterface.*
import com.example.emapp.view.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivityPresenter(_view: MainActivity): Presenter {

    private var view: MainActivity = _view
    private var model: Model = MainActivityModel()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

    init {
        view.initView()
    }

    override fun enterData(edEmail: TextInputLayout, edPassword: TextInputLayout){

        val emailValidator = EmailValidator()
        val passwordValidator = PasswordValidator()

        if((emailValidator.ValidateEmail(edEmail)) and (passwordValidator.ValidatePassword(edPassword))){
            firebaseAuth.signInWithEmailAndPassword(edEmail.editText?.text.toString(), edPassword.editText?.text.toString())
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        view.signInIntent()
                    } else {
                        view.toastWrong()
                    }
                }

        }
        else{
            if(!(emailValidator.ValidateEmail(edEmail))){
                edEmail.error = emailValidator.GetError()
            }
            if(!(passwordValidator.ValidatePassword(edPassword))){
                edPassword.error = passwordValidator.GetError()
            }
        }


    }

    override fun validateEmail(edEmail: TextInputLayout) {

        val emailValidator = EmailValidator()
        edEmail.error = null
        if(!(emailValidator.ValidateEmail(edEmail))){
            edEmail.error = emailValidator.GetError()
        }

    }

    override fun validatePassword(edPassword: TextInputLayout) {

        val passwordValidator = PasswordValidator()
        edPassword.error = null
        if(!(passwordValidator.ValidatePassword(edPassword))){
            edPassword.error = passwordValidator.GetError()
        }

    }


}