package com.amvlabs.rupeeinvest.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.amvlabs.rupeeinvest.R


class MyDialog(private val activity: Activity):AlertDialog(activity) {

    private val dialog = Dialog(activity)

    fun startLoading(){
        val inflater = activity.layoutInflater
        dialog.apply {
            setContentView(inflater.inflate(R.layout.custome_dialog,null))
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }.create()
        dialog.show()
    }
    fun stopLoading(){
        dialog.dismiss()
    }
}