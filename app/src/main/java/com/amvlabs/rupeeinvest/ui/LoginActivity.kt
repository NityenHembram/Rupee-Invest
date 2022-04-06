package com.amvlabs.rupeeinvest.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityLoginBinding
import com.amvlabs.rupeeinvest.dialogs.MyDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


private const val TAG = "tag"
class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    private lateinit var email:TextInputEditText
    private lateinit var password:TextInputEditText
    private lateinit var dialog:MyDialog


    private lateinit var bind:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)


        email = bind.loginEmail
        password = bind.loginPass

        dialog = MyDialog(this)



        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

        bind.createAccountBtn.setOnClickListener{
            startActivity(Intent(this,CreateAccountActivity::class.java))
            finish()
        }



        password.addTextChangedListener{
            if(it!!.length <= 7 ){
                bind.loginPassLayout.boxStrokeColor = Color.RED
                bind.loginPassLayout.helperText = "Password must contain 8 Characters"
            }else{
                bind.loginPassLayout.boxStrokeColor = Color.BLACK
                bind.loginPassLayout.helperText =""
            }
        }

        email.addTextChangedListener{
            if(it!!.endsWith("@gmail.com") || it.endsWith("@hotmail.com") || it.endsWith("@yahoo.com") ){
                bind.loginEmailLayout.helperText =""
            }else{
                bind.loginEmailLayout.helperText ="Enter a valid Email"


            }
        }


        bind.loginBtn.setOnClickListener{
            dialog.startLoading()
            login()
        }
        
        
    }

    private fun login() {
        val e = email.text.toString().trim()
        val p = password.text.toString().trim()
        Log.d(TAG, "login: ")

        when{
            TextUtils.isEmpty(e) ->{
                bind.loginEmailLayout.boxStrokeColor = Color.RED
                bind.loginEmailLayout.helperText = "Email must not be empty"
                dialog.stopLoading()
                return
            }
            TextUtils.isEmpty(p) ->{
                bind.loginPassLayout.boxStrokeColor = Color.RED
                bind.loginPassLayout.helperText = "password must not be empty"
                dialog.stopLoading()
                return
            }
        }

        Log.d(TAG, "login: ")
        auth.signInWithEmailAndPassword(e,p).addOnSuccessListener {
            dialog.stopLoading()
            startActivity(Intent(this,HomeActivity::class.java))
        }.addOnFailureListener{
            Log.d(TAG, "login: $it")
            dialog.stopLoading()
        }
    }

   
}