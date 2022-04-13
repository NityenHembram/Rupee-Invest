package com.amvlabs.rupeeinvest.onBoarding

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.adapters.OnBoardingPagerAdapter
import com.omni.onboardingscreen.domain.OnBoardingPrefManager
import kotlinx.android.synthetic.main.activity_main.view.*
import com.amvlabs.rupeeinvest.entity.OnBoardingPage
import com.amvlabs.rupeeinvest.ui.LoginActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import setParallaxTransformation


private var flag = false

class OnBoardView @JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attr, defStyleAttr, defStyleRes) {

    val c = context
    private val numberOfPages by lazy { OnBoardingPage.values().size }
    private val prefManager: OnBoardingPrefManager
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_main, this, true)
        setUpSlider(view)
        addingButtonsClickListeners()
       prefManager = OnBoardingPrefManager(view.context)
        if(!prefManager.isFirstTimeLaunch){
            context.startActivity(Intent(context,LoginActivity::class.java))
        }
    }

    private fun addingButtonsClickListeners() {
        nextBtn.setOnClickListener{ navigateToNextSlide() }
        skipBtn.setOnClickListener{
            setFirstTimeLaunchToFalse()
            context.startActivity(Intent(context,LoginActivity::class.java))

        }
        startBtn.setOnClickListener{
            setFirstTimeLaunchToFalse()
            context.startActivity(Intent(context,LoginActivity::class.java))
        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefManager.isFirstTimeLaunch = false
    }

    private fun navigateToNextSlide() {
       val nextSlidePos:Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos,true)
    }

    private fun setUpSlider(view: View) {
        with(slider){
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page,position)
            }
            addSlideChangeListener()

            val indicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            indicator.setViewPager2(this)
        }
    }

    private fun addSlideChangeListener() {
        slider.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if(numberOfPages > 1){
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

}


