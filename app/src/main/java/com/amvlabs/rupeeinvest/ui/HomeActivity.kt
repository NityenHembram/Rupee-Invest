package com.amvlabs.rupeeinvest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toolbar
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityHomeBinding
import com.amvlabs.rupeeinvest.fragments.*
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var bind: ActivityHomeBinding

    private lateinit var toolbar: Toolbar
    private lateinit var auth:FirebaseAuth

    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.toolbar)

        flag = intent.getBooleanExtra(isSignIn,false)

        auth = FirebaseAuth.getInstance()

        replaceFragment(HomeFragment())

        bind.bottomNavView.selectedItemId = R.id.item_home
        bind.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_invest -> {
                    replaceFragment(InvestFragment())
                    true
                }
                R.id.item_withdraw -> {
                    replaceFragment(WithdrawFragment())
                    true
                }
                R.id.item_home-> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.item_walletHistory -> {
                    replaceFragment(WalletHistoryFragment())
                    true
                }
                R.id.item_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> {false }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val bundle = Bundle()
        bundle.putBoolean(isSignIn,flag)
        fragment.arguments = bundle
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        val item = menu?.findItem(R.id.search_icon)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.singOut ->{
                auth.signOut()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            R.id.item_setting -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}