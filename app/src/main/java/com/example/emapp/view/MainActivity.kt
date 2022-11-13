package com.example.emapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.emapp.R
import com.example.emapp.contract.ContractInterface.*
import com.example.emapp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View {

    private var presenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
    }

    override fun initView() {
        EnterButton.setOnClickListener {
            presenter?.enterData(edEmail,edPassword)
        }
        CreateAccButton.setOnClickListener{
            startActivity(Intent( this, CreateAccount::class.java))
            finish()
        }
    }

    override fun errorMessege() {
        val toastErLogin = Toast.makeText(this,"fill all", Toast.LENGTH_LONG)
        toastErLogin.show()
    }

    override fun successMessege() {
        val toastLogin = Toast.makeText(this, "You are loggined", Toast.LENGTH_LONG)
        toastLogin.show()
    }

    override fun signInIntent() {
        startActivity(Intent( this, AccountUser::class.java))
        finish()
    }

}