package com.amvlabs.rupeeinvest.onBoarding

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amvlabs.rupeeinvest.R
import com.omni.onboardingscreen.domain.OnBoardingPrefManager

class OnBoardingActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_activity)


//        val prefManager:OnBoardingPrefManager = OnBoardingPrefManager(this)
//        Log.d("TAG", "onCreate: ${prefManager.isFirstTimeLaunch}")
//        if(prefManager.isFirstTimeLaunch){
//            finish()
//        }
    }
}