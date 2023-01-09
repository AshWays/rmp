/*
 * Copyright (c) 2023.
 * Medzitov Emir, 09.01.2023
 *
 */

package com.example.emapp.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.emapp.R
import com.example.emapp.contract.AccountUserInterface.*
import com.example.emapp.fragment.*
import com.example.emapp.presenter.AccountUserPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_account_user.*



class AccountUser : AppCompatActivity(), View {

    private var presenter: AccountUserPresenter? = null
    private val locationFragment = LocationFragment()
    private val settingsFragment = SettingsFragment()
    private val friendsFragment = FriendsFragment()
    private val userFragment = UserFragment()
    private val newsFragment = NewsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_user)
        replaceFragment(locationFragment)
        bottom_navigation.selectedItemId = R.id.ic_map

        presenter = AccountUserPresenter(this)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_map -> replaceFragment(locationFragment)
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
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun replaceNav(){
        bottom_navigation.selectedItemId = R.id.ic_map
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

    fun refreshActivity(){
        val refresh = Intent(AccountUser@this, AccountUser::class.java)
        startActivity(refresh)
    }


}