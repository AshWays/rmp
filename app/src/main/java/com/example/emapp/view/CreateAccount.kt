package com.example.emapp.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.emapp.R
import com.example.emapp.presenter.CreateAccountPresenter
import com.example.emapp.contract.CreateAccountInterface.*
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : AppCompatActivity(), View {

    private var presenter: CreateAccountPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        presenter = CreateAccountPresenter(this)
    }


    override fun initView() {
        CreateButton.setOnClickListener {
            presenter?.createAccount(edCreateEmail,edCreatePassword,edConfirmPassword)
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

    override fun notIdenticMessege() {
        val toastNotIdentic = Toast.makeText(this, "Not identic passwords", Toast.LENGTH_LONG)
        toastNotIdentic.show()
    }

    override fun signInIntent() {
        startActivity(Intent( this, AccountUser::class.java))
        finish()
    }
}