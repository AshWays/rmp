package com.example.emapp.presenter


import com.example.emapp.classes.EmailValidatorMainActivity
import com.example.emapp.classes.PasswordValidatorMainActivity
import com.example.emapp.model.MainActivityModel
import com.example.emapp.contract.ContractInterface.*
import com.example.emapp.view.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityPresenter(_view: MainActivity): Presenter {

    private var view: MainActivity = _view
    private var model: Model = MainActivityModel()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

    init {
        view.initView()
    }

    override fun enterData(edEmail: String, edPassword: String){

        val emailValidator = EmailValidatorMainActivity(view)
        val passwordValidator = PasswordValidatorMainActivity(view)

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
                view.edEmail.error = emailValidator.GetError()
            }
            if(!(passwordValidator.ValidatePassword(edPassword))){
                view.edPassword.error = passwordValidator.GetError()
            }
        }


    }

    override fun validateEmail(edEmail: String) {

        val emailValidator = EmailValidatorMainActivity(view)
        view.edEmail.error = null
        if(!(emailValidator.ValidateEmail(edEmail))){
            view.edEmail.error = emailValidator.GetError()
        }

    }

    override fun validatePassword(edPassword: String) {

        val passwordValidator = PasswordValidatorMainActivity(view)
        view.edPassword.error = null
        if(!(passwordValidator.ValidatePassword(edPassword))){
            view.edPassword.error = passwordValidator.GetError()
        }

    }


}