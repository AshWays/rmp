package com.example.emapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.emapp.R
import com.example.emapp.contract.AccountUserInterface.*
import com.example.emapp.fragment.*
import com.example.emapp.presenter.AccountUserPresenter
import com.example.emapp.presenter.CreateAccountPresenter
import com.google.android.gms.dynamic.SupportFragmentWrapper
import kotlinx.android.synthetic.main.activity_account_user.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_settings.*

class AccountUser : AppCompatActivity(), View {

    private var presenter: AccountUserPresenter? = null
    private val mapFragment = MapFragment()
    private val settingsFragment = SettingsFragment()
    private val friendsFragment = FriendsFragment()
    private val userFragment = UserFragment()
    private val newsFragment = NewsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_user)
        replaceFragment(mapFragment)



        presenter = AccountUserPresenter(this)

    }

    override fun initView() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_map -> replaceFragment(mapFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
                R.id.ic_user -> replaceFragment(userFragment)
                R.id.ic_friends -> replaceFragment(friendsFragment)
                R.id.ic_news -> replaceFragment(newsFragment)
            }
            true

        }
    }

    override fun replaceFragment(fragment: Fragment) {
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun logOut() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}