package com.amvlabs.rupeeinvest.onBoarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityLoginBinding
import com.amvlabs.rupeeinvest.databinding.OnboardingActivityBinding
import com.amvlabs.rupeeinvest.databinding.OnboardingItemBinding

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: OnboardingActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = OnboardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }
}