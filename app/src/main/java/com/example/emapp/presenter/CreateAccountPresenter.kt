package com.example.emapp.presenter

import android.content.Intent
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity
import com.example.emapp.classes.EmailValidator
import com.example.emapp.classes.PasswordValidator
import com.example.emapp.model.CreateAccountModel
import com.example.emapp.contract.CreateAccountInterface.*
import com.example.emapp.view.CreateAccount
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateAccountPresenter(_view: CreateAccount): Presenter {

    private var view: CreateAccount = _view
    private var model: Model = CreateAccountModel()


    init {
        view.initView()
    }

    override fun createAccount(edCreateEmail: TextInputLayout, edCreatePassword: TextInputLayout, edConfirmPassword: TextInputLayout){

        val emailValidator = EmailValidator()
        val passwordValidator = PasswordValidator()
        val firebaseAuth: FirebaseAuth = model.getFirebaseAuth()

        if((emailValidator.ValidateEmail(edCreateEmail)) and (passwordValidator.ValidatePassword(edCreatePassword))) {
            if ((edConfirmPassword.editText?.text.toString()) == (edCreatePassword.editText?.text.toString())) {
                firebaseAuth.createUserWithEmailAndPassword(edCreateEmail.editText?.text.toString(),edCreatePassword.editText?.text.toString())
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            view.signInIntent()
                        }
                        else{
                            view.toastWrong()
                        }
                    }
            }
            else edConfirmPassword.error = "Not identyc passwords"
        }
        else {
            if(!(emailValidator.ValidateEmail(edCreateEmail))){
                edCreateEmail.error = emailValidator.GetError()
            }
            if(!(passwordValidator.ValidatePassword(edCreatePassword))){
                edCreatePassword.error = passwordValidator.GetError()
            }
        }
    }

    override fun validateEmail(edCreateEmail: TextInputLayout) {

        val emailValidator = EmailValidator()
        edCreateEmail.error = null
        if(!(emailValidator.ValidateEmail(edCreateEmail))){
            edCreateEmail.error = emailValidator.GetError()
        }

    }

    override fun validatePassword(edCreatePassword: TextInputLayout) {
        val passwordValidator = PasswordValidator()
        edCreatePassword.error = null
        if(!(passwordValidator.ValidatePassword(edCreatePassword))){
            edCreatePassword.error = passwordValidator.GetError()
        }


    }



}