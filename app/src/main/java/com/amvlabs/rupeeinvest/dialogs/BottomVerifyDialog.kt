package com.amvlabs.rupeeinvest.dialogs

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.listeners.TextChangeListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class BottomVerifyDialog(private val onVerifyClickListener:VerifyClickListener)  : BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.bottomsheet_dialog_layout,container,false)


        val verifyBtn = v.findViewById<AppCompatButton>(R.id.verify_btn)

        val otp_1 = v.findViewById<TextInputEditText>(R.id.otp_1)
        val otp_2 = v.findViewById<TextInputEditText>(R.id.otp_2)
        val otp_3 = v.findViewById<TextInputEditText>(R.id.otp_3)
        val otp_4 = v.findViewById<TextInputEditText>(R.id.otp_4)
        val otp_5 = v.findViewById<TextInputEditText>(R.id.otp_5)
        val otp_6 = v.findViewById<TextInputEditText>(R.id.otp_6)

        val editTextList = arrayListOf<TextInputEditText>(otp_1,otp_2,otp_3,otp_4,otp_5,otp_6)

        otp_1.addTextChangedListener(TextChangeListener(otp_1, editTextList))
        otp_2.addTextChangedListener(TextChangeListener(otp_2, editTextList))
        otp_3.addTextChangedListener(TextChangeListener(otp_3, editTextList))
        otp_4.addTextChangedListener(TextChangeListener(otp_4, editTextList))
        otp_5.addTextChangedListener(TextChangeListener(otp_5, editTextList))
        otp_6.addTextChangedListener(TextChangeListener(otp_6, editTextList))





        verifyBtn.setOnClickListener{
            val otp:String = otp_1.text.toString()+otp_2.text.toString()+otp_3.text.toString()+otp_4.text.toString()+otp_5.text.toString()+otp_6.text.toString()
            if(otp.length == 6){
                onVerifyClickListener.onClickVerify(otp)
            }else{
                val animation = AnimationUtils.loadAnimation(v.context,R.anim.otp_animation)
                v.findViewById<LinearLayoutCompat>(R.id.otpLayout).startAnimation(animation)
            }

        }
        return v
    }

}

interface VerifyClickListener{
    fun onClickVerify(otp:String)
}