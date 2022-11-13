package com.example.emapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emapp.R
import com.example.emapp.contract.AccountUserInterface.*
import com.example.emapp.presenter.AccountUserPresenter
import com.example.emapp.presenter.CreateAccountPresenter
import kotlinx.android.synthetic.main.activity_account_user.*

class AccountUser : AppCompatActivity(), View {

    private var presenter: AccountUserPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_user)

        presenter = AccountUserPresenter(this)

    }

    override fun initView() {
        SignOutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}