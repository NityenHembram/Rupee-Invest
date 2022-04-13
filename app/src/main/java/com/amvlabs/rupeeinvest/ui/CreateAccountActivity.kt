package com.amvlabs.rupeeinvest.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.widget.addTextChangedListener
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityCreateAccountBinding
import com.amvlabs.rupeeinvest.dialogs.BottomVerifyDialog
import com.amvlabs.rupeeinvest.dialogs.MyDialog
import com.amvlabs.rupeeinvest.dialogs.VerifyClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_create_account.*
import java.util.concurrent.TimeUnit


private const val TAG = "tag"
const val PHONE_KEY = "phone"

class CreateAccountActivity : AppCompatActivity(), VerifyClickListener {

    private lateinit var dialog: MyDialog

    lateinit var auth: FirebaseAuth
    lateinit var credential: PhoneAuthCredential
    lateinit var getToken: PhoneAuthProvider.ForceResendingToken
    lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    private var verificationId = ""
    private var smsCode = ""

    private lateinit var bottomSheet:BottomVerifyDialog

    private lateinit var bind: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(bind.root)

        auth = FirebaseAuth.getInstance()
        dialog = MyDialog(this)

        bottomSheet = BottomVerifyDialog(this)


        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d(TAG, "onVerificationFailed: $e")
            }


            override fun onCodeSent(code: String, token: PhoneAuthProvider.ForceResendingToken) {
                verificationId = code
                getToken = token
                dialog.stopLoading()
                showBottomDialog()
            }
        }

        validateUser()

//        Create Account button
        bind.createBtn.setOnClickListener {
            phoneAuthentication()
            dialog.startLoading()
        }


//        Back to LoginScreen
        bind.backLogIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateUser() {

        var checkFirstName: Boolean = false
        var checkLastName: Boolean = false
        var checkEmail: Boolean = false
        var checkPhone: Boolean = false
        var checkPassword: Boolean = false
        var checkConPass: Boolean = false

        fun checkAll() {
            if (checkEmail && checkPhone && checkPassword && checkConPass) {
                bind.createBtn.isEnabled = true
                bind.btn.setCardBackgroundColor(Color.BLUE)
            } else {
                bind.createBtn.isEnabled = false
                bind.btn.setCardBackgroundColor(Color.GRAY)
            }
        }


        bind.passEdt.addTextChangedListener {
            if (it!!.length <= 7) {
                bind.passCreatelout.boxStrokeColor = Color.RED
                bind.passCreatelout.helperText = "Password must contain 8 Characters"
                checkPassword = false
            } else {
                bind.passCreatelout.boxStrokeColor = Color.BLACK
                bind.passCreatelout.helperText = ""
                checkPassword = true
            }
            checkAll()
        }

        bind.conPassEdt.addTextChangedListener {
            if (it.toString() == bind.passEdt.text.toString()) {
                bind.conPassLout.boxStrokeColor = Color.BLACK
                bind.conPassLout.helperText = ""
                checkConPass = true
            } else {
                bind.conPassLout.boxStrokeColor = Color.RED
                bind.conPassLout.helperText = "Password don't match"
                checkConPass = false
            }
            checkAll()
        }

        bind.phoneNumEdt.addTextChangedListener {
            if (it?.length!! < 10) {
                bind.phnCreateLout.helperText = "Enter a Valid Phone Number"
                bind.phnCreateLout.boxStrokeColor = Color.RED
                checkPhone = false
            } else {
                bind.phnCreateLout.helperText = ""
                bind.phnCreateLout.boxStrokeColor = Color.BLACK
                checkPhone = true
            }
            checkAll()
        }


        bind.emailEdt.addTextChangedListener {
            if (it!!.contains("@") && it.endsWith(".com")) {
                bind.emailCreateLout.boxStrokeColor = Color.BLACK
                bind.emailCreateLout.helperText = ""
                checkEmail = true
            } else {
                bind.emailCreateLout.boxStrokeColor = Color.RED
                bind.emailCreateLout.helperText = "Enter Valid Email"
                checkEmail = false
            }
            checkAll()
        }

        bind.firstNameEdt.addTextChangedListener {
            if (TextUtils.isEmpty(it)) {
                bind.firstNameEdtLout.boxStrokeColor = Color.RED
                bind.firstNameEdtLout.helperText = "Enter Valid Email"
                checkFirstName = false
            } else {
                bind.firstNameEdtLout.boxStrokeColor = Color.BLACK
                bind.firstNameEdtLout.helperText = ""
                checkFirstName = true
            }
            checkAll()
        }


        bind.lastNameEdt.addTextChangedListener {
            if (TextUtils.isEmpty(it)) {
                bind.lastNameEdtLout.boxStrokeColor = Color.RED
                bind.lastNameEdtLout.helperText = "Enter Valid Email"
                checkLastName = false
            } else {
                bind.lastNameEdtLout.boxStrokeColor = Color.BLACK
                bind.lastNameEdtLout.helperText = ""
                checkLastName = true
            }
            checkAll()
        }


    }


    private fun phoneAuthentication() {
        val options = PhoneAuthOptions.newBuilder(auth).apply {
            setPhoneNumber("+91${bind.phoneNumEdt.text.toString().trim()}")
            setTimeout(60L, TimeUnit.SECONDS)
            setActivity(this@CreateAccountActivity)
            setCallbacks(callback)
        }.build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    private fun showBottomDialog() {
//        bottomSheet.isCancelable = false
        bottomSheet.show(supportFragmentManager, "bottomSheet")
    }

    override fun onClickVerify(otp: String) {
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, otp)
        linkedPhoneNumber(credential)
    }

    private fun linkedPhoneNumber(p0: PhoneAuthCredential) {

        val email = bind.emailEdt.text.toString()
        val password = bind.passEdt.text.toString()

        auth.signInWithCredential(p0).addOnSuccessListener {
            val credential = EmailAuthProvider.getCredential(email, password)
            auth.currentUser!!.linkWithCredential(credential).addOnSuccessListener {
                dialog.stopLoading()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                dialog.stopLoading()
                Log.d(TAG, "email:  $it")
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                auth.currentUser!!.delete().addOnSuccessListener {
                    dialog.stopLoading()
                }.addOnFailureListener {
                    dialog.stopLoading()
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "delete: $it ")
                }
            }
        }.addOnFailureListener {
            dialog.stopLoading()
            Log.d(TAG, "linkedPhoneNumber: $it ")
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
        }
    }


}