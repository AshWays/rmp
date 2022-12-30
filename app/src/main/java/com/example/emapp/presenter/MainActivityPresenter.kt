package com.example.emapp.presenter


import com.example.emapp.R
import com.example.emapp.classes.EmailValidator
import com.example.emapp.classes.PasswordValidator
import com.example.emapp.model.MainActivityModel
import com.example.emapp.contract.ContractInterface.*
import com.example.emapp.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityPresenter(_view: MainActivity): Presenter {

    private var view: MainActivity = _view
    private var model: Model = MainActivityModel()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser


    override fun enterData(edEmail: String, edPassword: String){

        val emailValidator = EmailValidator()
        val passwordValidator = PasswordValidator()

        if(edEmail.isEmpty()) {
            view.edEmail.error = view.getString(R.string.fill_field)
            return
        }

        if(edPassword.isEmpty()) {
            view.edPassword.error = view.getString(R.string.fill_field)
            return
        }

        if((emailValidator.ValidateEmail(edEmail))
            and (passwordValidator.ValidatePassword(edPassword))){
            firebaseAuth.signInWithEmailAndPassword(edEmail, edPassword)
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
                view.edEmail.error = view.getString(R.string.invalid_email)
            }
            if(!(passwordValidator.ValidatePassword(edPassword))){
                view.edPassword.error = view.getString(R.string.short_password)
            }
        }


    }

    override fun validateEmail(edEmail: String) {

        if(edEmail.isEmpty()) {
            view.edEmail.error = view.getString(R.string.fill_field)
            return
        }

        val emailValidator = EmailValidator()
        view.edEmail.error = null
        if(!(emailValidator.ValidateEmail(edEmail))){
            view.edEmail.error = view.getString(R.string.invalid_email)
        }

    }

    override fun validatePassword(edPassword: String) {

        if(edPassword.isEmpty()) {
            view.edPassword.error = view.getString(R.string.fill_field)
            return
        }

        val passwordValidator = PasswordValidator()
        view.edPassword.error = null
        if(!(passwordValidator.ValidatePassword(edPassword))){
            view.edPassword.error = view.getString(R.string.short_password)
        }

    }


}