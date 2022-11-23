package com.example.emapp.view
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
            presenter?.createAccount(edCreateEmail.editText?.text.toString(),
                edCreatePassword.editText?.text.toString(),
                edConfirmPassword.editText?.text.toString())
        }

        edCreateEmail.editText?.addTextChangedListener {
            presenter?.validateEmail(edCreateEmail.editText?.text.toString())
        }

        edCreatePassword.editText?.addTextChangedListener {
            presenter?.validatePassword(edCreatePassword.editText?.text.toString())
        }


        edConfirmPassword.editText?.addTextChangedListener {
            edConfirmPassword.error = null
        }
    }

    override fun signInIntent() {
        startActivity(Intent( this, AccountUser::class.java))
        finish()
    }

    override fun toastWrong() {
        Toast.makeText(this,getString(R.string.server_not_responding),Toast.LENGTH_LONG).show()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val view = this.currentFocus
        when(event?.actionMasked){
            MotionEvent.ACTION_DOWN ->{
                val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                hideMe.hideSoftInputFromWindow(view?.windowToken,0)
            }
        }

        return true
    }

}