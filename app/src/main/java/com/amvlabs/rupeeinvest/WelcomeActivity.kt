package com.amvlabs.rupeeinvest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.amvlabs.rupeeinvest.onBoarding.OnBoardingActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this,OnBoardingActivity::class.java))
            finish()
        },1000)

    }
}