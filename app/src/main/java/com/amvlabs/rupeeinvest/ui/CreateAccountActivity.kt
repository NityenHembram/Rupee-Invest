package com.amvlabs.rupeeinvest.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth



private const val TAG = "tag"
const val PHONE_KEY = "phone"
class CreateAccountActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var bind : ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initialising()
        bind.createBtn.setOnClickListener{
            createUser()
        }
        bind.backLogIn.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }

    private fun initialising() {
        firebaseAuth = FirebaseAuth.getInstance()
    }
    private fun createUser() {
        val firstName = bind.firstNameEdt
        val lastName = bind.lastNameEdt
        val email_txt = bind.emailEdt
        val password = bind.passEdt
        val con_pass = bind.conPassEdt
        val phone = bind.phoneNumEdt

        when{
            TextUtils.isEmpty(firstName.text.toString()) -> {
                bind.firstNameEdtLout.boxStrokeColor = Color.RED
                bind.firstNameEdtLout.helperText = "Mandatory"
                return
            }
            TextUtils.isEmpty(lastName.text.toString()) -> {
                bind.lastNameEdtLout.boxStrokeColor = Color.RED
                bind.lastNameEdtLout.helperText = "Mandatory"
                return
            }
            TextUtils.isEmpty(email_txt.text.toString()) -> {
                bind.emailCreateLout.boxStrokeColor = Color.RED
                bind.emailCreateLout.helperText = "Mandatory"
                return
            }
            TextUtils.isEmpty(phone.text.toString()) -> {
                bind.phnCreateLout.boxStrokeColor = Color.RED
                bind.phnCreateLout.helperText = "Mandatory"
                return
            }
            TextUtils.isEmpty(password.text.toString()) -> {
                bind.passCreatelout.boxStrokeColor = Color.RED
                bind.passCreatelout.helperText = "Mandatory"
                return
            }
            TextUtils.isEmpty(con_pass.text.toString()) -> {
                bind.conPassLout.boxStrokeColor = Color.RED
                bind.conPassLout.helperText = "Mandatory"
                return
            }

        }

        firebaseAuth.createUserWithEmailAndPassword(email_txt.text.toString().trim(),password.text.toString().trim()).addOnSuccessListener {
            Log.d(TAG, "createUser: $it")
            val intent = Intent(this,VerifyActivity::class.java)
            intent.putExtra(PHONE_KEY,phone.text.toString().trim())
            startActivity(intent)
        }.addOnFailureListener{
           Toast.makeText(this,"${it.stackTrace}",Toast.LENGTH_SHORT).show()
        }
    }


}