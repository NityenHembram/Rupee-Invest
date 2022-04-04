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
    lateinit var auth: FirebaseAuth
    lateinit var credential: PhoneAuthCredential
    lateinit var getToken:PhoneAuthProvider.ForceResendingToken
    lateinit var callback:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private var verificationId = ""
    private var smsCode = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        phone = intent.getStringExtra("phone").toString()
        val newPhone = "+91$phone"

        initialisation()



        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d(TAG, "onVerificationFailed: $e")
            }

            override fun onCodeSent(code: String, token: PhoneAuthProvider.ForceResendingToken) {
                verificationId = code
                getToken = token
            }

        }
        val options = PhoneAuthOptions.newBuilder(auth).apply {
            setPhoneNumber(newPhone)
            setTimeout(60L,TimeUnit.SECONDS)
            setActivity(this@VerifyActivity)
            setCallbacks(callback)
        }.build()


        PhoneAuthProvider.verifyPhoneNumber(options)

        verifyBtn.setOnClickListener{
            smsCode = verifyTxt.text.toString()
            val credential:PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,smsCode)
            linkedPhoneNumber(credential)

        }

    }

    private fun linkedPhoneNumber(p0: PhoneAuthCredential) {
      auth.currentUser?.linkWithCredential(p0)?.addOnSuccessListener {
          Log.d(TAG, "verifyCode: ${it.additionalUserInfo}")
      }?.addOnFailureListener{
          Log.d(TAG, "verifyCode: ${it.toString()}")
      }
    }

    private fun initialisation() {
        verifyTxt = findViewById(R.id.verify_edt)
        verifyBtn = findViewById(R.id.verify_btn)

        auth = FirebaseAuth.getInstance()
    }




}