package com.example.emapp.presenter

import com.example.emapp.R
import com.example.emapp.classes.EmailValidator
import com.example.emapp.classes.PasswordValidator
import com.example.emapp.model.CreateAccountModel
import com.example.emapp.contract.CreateAccountInterface.*
import com.example.emapp.view.CreateAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountPresenter(_view: CreateAccount): Presenter {

    private var view: CreateAccount = _view
    private var model: Model = CreateAccountModel()
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val firebaseAuth: FirebaseAuth = model.getFirebaseAuth()

    override fun createAccount(edCreateEmail: String, edCreatePassword: String, edConfirmPassword: String){

        if(edCreateEmail.isEmpty()) {
            view.edCreateEmail.error = view.getString(R.string.fill_field)
            return
        }

        if(edCreatePassword.isEmpty()) {
            view.edCreatePassword.error = view.getString(R.string.fill_field)
            return
        }

        if(edConfirmPassword.isEmpty()) {
            view.edConfirmPassword.error = view.getString(R.string.fill_field)
            return
        }

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
                view.edCreateEmail.error = view.getString(R.string.invalid_email)
            }
            if(!(passwordValidator.ValidatePassword(edCreatePassword))){
                view.edCreatePassword.error = view.getString(R.string.short_password)
            }
        }
    }

    override fun validateEmail(edCreateEmail: String) {

        if(edCreateEmail.isEmpty()) {
            view.edCreateEmail.error = view.getString(R.string.fill_field)
            return
        }
        val emailValidator = EmailValidator()
        view.edCreateEmail.error = null
        if(!(emailValidator.ValidateEmail(edCreateEmail))){
            view.edCreateEmail.error = view.getString(R.string.invalid_email)
        }

    }

    override fun validatePassword(edCreatePassword: String) {
        if(edCreatePassword.isEmpty()) {
            view.edCreatePassword.error = view.getString(R.string.fill_field)
            return
        }
        val passwordValidator = PasswordValidator()
        view.edCreatePassword.error = null
        if(!(passwordValidator.ValidatePassword(edCreatePassword))){
            view.edCreatePassword.error = view.getString(R.string.short_password)
        }


    }



}