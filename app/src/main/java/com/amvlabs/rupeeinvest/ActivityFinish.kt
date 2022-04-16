package com.amvlabs.rupeeinvest

import com.amvlabs.rupeeinvest.onBoarding.OnBoardingActivity

class ActivityFinish:OnBoardActivityFinish {
    override fun finishActivity(activity:OnBoardingActivity) {
        activity.finish()
    }
}

interface OnBoardActivityFinish{
    fun finishActivity(activity: OnBoardingActivity)
}