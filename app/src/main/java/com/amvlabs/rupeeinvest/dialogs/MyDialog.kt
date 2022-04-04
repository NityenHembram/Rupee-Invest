package com.amvlabs.rupeeinvest.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.amvlabs.rupeeinvest.R


class MyDialog(private val activity: Activity):AlertDialog(activity) {

    private val dialog = AlertDialog.Builder(activity)

    fun startLoading(){
        val inflater = activity.layoutInflater
        dialog.apply {
            setView(inflater.inflate(R.layout.custome_dialog,null))
            setCancelable(false)

        }.create()
        dialog.show()
    }
    fun stopLoading(dialog:MyDialog){
        dialog.hide()
    }
}