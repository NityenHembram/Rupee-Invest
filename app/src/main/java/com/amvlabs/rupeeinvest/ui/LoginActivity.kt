package com.amvlabs.rupeeinvest.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityLoginBinding
import com.amvlabs.rupeeinvest.dialogs.MyDialog
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.math.log


private const val TAG = "tag"
class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var dialog:MyDialog

    private lateinit var  googleSignClint:GoogleSignInClient
    private lateinit var googleSignInAccount:GoogleSignInAccount

    private lateinit var bind:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)


//        Initialize Dialog
        dialog = MyDialog(this)


//        Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()


        if(auth.currentUser != null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }


        loginWithGoogle()


        bind.createAccountBtn.setOnClickListener{
            startActivity(Intent(this,CreateAccountActivity::class.java))
            finish()
        }

//        bind.loginGoogle.setOnClickListener{
//            val signInIntent = googleSignClint.signInIntent
//            startActivityForResult.launch(signInIntent)
//        }



        bind.loginPass.addTextChangedListener{
            if(it!!.length <= 7 ){
                bind.loginPassLayout.boxStrokeColor = Color.RED
                bind.loginPassLayout.helperText = "Password must contain 8 Characters"
            }else{
                bind.loginPassLayout.boxStrokeColor = Color.BLACK
                bind.loginPassLayout.helperText = ""
            }
        }


        bind.loginEmail.addTextChangedListener{
            if(it!!.contains("@") && it.endsWith(".com") || it.length == 10 ){
                bind.loginEmailLayout.boxStrokeColor = Color.BLACK
                bind.loginEmailLayout.helperText = ""
            }else{
                bind.loginEmailLayout.boxStrokeColor = Color.RED
                bind.loginEmailLayout.helperText = "Enter Valid Email"
            }
        }



        bind.loginBtn.setOnClickListener{
            dialog.startLoading()
            login()
        }
        
        
    }

    private fun loginWithGoogle(){
     val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
         .requestEmail().build()
        googleSignClint = GoogleSignIn.getClient(this,gso)
//        if(GoogleSignIn.getLastSignedInAccount(this) == null){
//            googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)!!
//        }

    }

//    private val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
//        ActivityResultCallback { result ->
//            if(result.resultCode == Activity.RESULT_OK){
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                task.addOnSuccessListener {
//                    Log.d(TAG, "success ${it.displayName}: ")
//
//                    startActivity(Intent(this,HomeActivity::class.java))
//                    finish()
//                }.addOnFailureListener{
//                    Log.d(TAG, "googleFail: $it ")
//                }
//            }
//        })

    private fun login() {
        val e = bind.loginEmail.text.toString().trim()
        val p = bind.loginPass.text.toString().trim()

        when{
            TextUtils.isEmpty(e) ->{
                bind.loginEmailLayout.boxStrokeColor = Color.RED
                bind.loginEmailLayout.helperText = "Email must not be empty"
                dialog.stopLoading()
                return
            }
            TextUtils.isEmpty(p) ->{
                bind.loginPassLayout.boxStrokeColor = Color.RED
                bind.loginPassLayout.helperText = "Password must not be empty"
                dialog.stopLoading()
                return
            }
        }

        if(p.length < 8){
            bind.loginPassLayout.helperText = "Password should be 8 character"
        }else{
            val credential = EmailAuthProvider.getCredential(e, p)
            auth.signInWithCredential(credential).addOnSuccessListener {
                dialog.stopLoading()
                startActivity(Intent(this,HomeActivity::class.java))
            }.addOnFailureListener{
               Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                dialog.stopLoading()
            }
        }




    }


    override fun onStart() {
        super.onStart()
//        Log.d(TAG, "onStart: $googleSignInAccount")
        if(auth.currentUser != null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }





}
