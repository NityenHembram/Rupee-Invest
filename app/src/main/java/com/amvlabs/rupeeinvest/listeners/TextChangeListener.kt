package com.amvlabs.rupeeinvest.listeners

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.amvlabs.rupeeinvest.R
import com.google.android.material.textfield.TextInputEditText

class TextChangeListener(var v: View,var editText:ArrayList<TextInputEditText>) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
        val text = p0.toString()
        when(v.id){
            R.id.otp_1 -> {
                if(text.length == 1){
                    editText[1].requestFocus()
                }
            }
            R.id.otp_2 -> {
                if(text.length == 1){
                    editText[2].requestFocus()
                }else if(text.isEmpty()){
                    editText[0].requestFocus()
                }
            }
            R.id.otp_3 -> {
                if(text.length == 1){
                    editText[3].requestFocus()
                }else if(text.isEmpty()){
                    editText[1].requestFocus()
                }

            }
            R.id.otp_4 -> {
                if(text.length == 1){
                    editText[4].requestFocus()
                }else if(text.isEmpty()){
                    editText[2].requestFocus()
                }

            }
            R.id.otp_5 -> {
                if(text.length == 1){
                    editText[5].requestFocus()
                }else if(text.isEmpty()){
                    editText[3].requestFocus()
                }
            }
            R.id.otp_6 -> {
//                if(text.length == 1){
//                    editText[].requestFocus()
                if(text.isEmpty()){
                    editText[4].requestFocus()
                }
            }

        }
    }
}