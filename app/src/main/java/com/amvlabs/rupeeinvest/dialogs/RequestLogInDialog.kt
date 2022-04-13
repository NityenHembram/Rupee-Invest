package com.amvlabs.rupeeinvest.dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import com.amvlabs.rupeeinvest.ui.LoginActivity

class RequestLogInDialog {
    fun showDialog(context:Context){
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setMessage("You Have to LogIn to View more details")
        builder1.setCancelable(true)
        builder1.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            context.startActivity(Intent(context, LoginActivity::class.java))
        })

        builder1.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}