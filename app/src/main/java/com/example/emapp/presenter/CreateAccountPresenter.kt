package com.example.emapp.presenter

import com.example.emapp.R
import com.example.emapp.classes.EmailValidatorCreateAccount
import com.example.emapp.classes.PasswordValidatorCreateAccount
import com.example.emapp.model.CreateAccountModel
import com.example.emapp.contract.CreateAccountInterface.*
import com.example.emapp.view.CreateAccount
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountPresenter(_view: CreateAccount): Presenter {

    private var view: CreateAccount = _view
    private var model: Model = CreateAccountModel()


    init {
        view.initView()
    }

    override fun createAccount(edCreateEmail: String, edCreatePassword: String, edConfirmPassword: String){

        val emailValidator = EmailValidatorCreateAccount(view)
        val passwordValidator = PasswordValidatorCreateAccount(view)
        val firebaseAuth: FirebaseAuth = model.getFirebaseAuth()

        if((emailValidator.ValidateEmail(edCreateEmail))
            and (passwordValidator.ValidatePassword(edCreatePassword))) {
            if ((edConfirmPassword) == (edCreatePassword)) {
                firebaseAuth.createUserWithEmailAndPassword(edCreateEmail,edCreatePassword)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            view.signInIntent()
                        }
                        else{
                            view.toastWrong()
                        }
                    }
            }
            else view.edConfirmPassword.error = view.getString(R.string.not_identic_passwords)
        }
        else {
            if(!(emailValidator.ValidateEmail(edCreateEmail))){
                view.edCreateEmail.error = emailValidator.GetError()
            }
            if(!(passwordValidator.ValidatePassword(edCreatePassword))){
                view.edCreatePassword.error = passwordValidator.GetError()
            }
        }
    }

    override fun validateEmail(edCreateEmail: String) {

        val emailValidator = EmailValidatorCreateAccount(view)
        view.edCreateEmail.error = null
        if(!(emailValidator.ValidateEmail(edCreateEmail))){
            view.edCreateEmail.error = emailValidator.GetError()
        }

    }

    override fun validatePassword(edCreatePassword: String) {
        val passwordValidator = PasswordValidatorCreateAccount(view)
        view.edCreatePassword.error = null
        if(!(passwordValidator.ValidatePassword(edCreatePassword))){
            view.edCreatePassword.error = passwordValidator.GetError()
        }


    }



}