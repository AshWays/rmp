package com.example.emapp.presenter

import android.widget.EditText
import com.example.emapp.model.MainActivityModel
import com.example.emapp.contract.ContractInterface.*
import com.example.emapp.model.FirebaseUtils.firebaseAuth
import com.example.emapp.view.MainActivity
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

    override fun enterData(edEmail: EditText, edPassword: EditText){

        if ((edEmail.text.isNotEmpty()) and (edPassword.text.isNotEmpty())) {
        firebaseAuth.signInWithEmailAndPassword(edEmail.text.toString().trim(), edPassword.text.toString().trim())
            .addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    view.successMessege()
                    view.signInIntent()
                } else {
                    view.errorMessege()
                }
            }
        }
        else view.errorMessege()

    }
}