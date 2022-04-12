package com.amvlabs.rupeeinvest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.amvlabs.rupeeinvest.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

private const val TAG = "tag"
class VerifyActivity : AppCompatActivity() {
    lateinit var verifyTxt:EditText
    lateinit var verifyBtn:Button

    var phone:String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        phone = intent.getStringExtra("phone").toString()
        val newPhone = "+91$phone"

        initialise()







//        PhoneAuthProvider.verifyPhoneNumber(options)

//        verifyBtn.setOnClickListener{
//            smsCode = verifyTxt.text.toString()
//            val credential:PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,smsCode)
//            linkedPhoneNumber(credential)
//
//        }

    }


    private fun initialise() {
//        verifyTxt = findViewById(R.id.verify_edt)
//        verifyBtn = findViewById(R.id.verify_btn)
//        auth = FirebaseAuth.getInstance()
    }
}