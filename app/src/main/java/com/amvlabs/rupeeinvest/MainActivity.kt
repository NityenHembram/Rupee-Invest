package com.amvlabs.rupeeinvest

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.amvlabs.rupeeinvest.adapters.ViewPagerAdapter
import com.amvlabs.rupeeinvest.databinding.ActivityMainBinding
import com.amvlabs.rupeeinvest.model.ViewPagerItem
import com.amvlabs.rupeeinvest.ui.LoginActivity
import java.util.prefs.AbstractPreferences


private var flag = false
class MainActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager2

    private lateinit var dotsLout:LinearLayout
    private lateinit var dots:Array<TextView?>

    private lateinit var getStart:Button

    private lateinit var sharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        Nityen Hembram

        sharePreferences = getSharedPreferences("myPreferences", MODE_PRIVATE)
        val edit = sharePreferences.edit()

        val retrive = sharePreferences.getBoolean("flag",flag)

        if(retrive == false){
            flag = true
            edit.putBoolean("flag",flag)
            edit.apply()
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }


        dotsLout = findViewById(R.id.linerLout)
        viewPager = findViewById(R.id.viewPager)
        getStart = findViewById(R.id.getStart_btn)
        var itemList = ArrayList<ViewPagerItem>()

        var texts = arrayListOf<Int>(R.string.firstSlideTxt,R.string.secSlidTxt,R.string.thirdSlidTxt,R.string.forthSlidTxt)
        var images = arrayListOf<Int>(R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d)
        for(i in 0 until texts.size){
            itemList.add(ViewPagerItem(getString(texts[i]),getDrawable(images[i])))
        }
        getStart.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        viewPager.adapter = ViewPagerAdapter(itemList)
        viewPager.getChildAt(0).overScrollMode = View.OVER_SCROLL_ALWAYS

        addDots(0)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                addDots(position)
                Log.d("TAG", "onPageSelected: $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun addDots(position:Int) {
        dots = arrayOfNulls<TextView>(4)
        dotsLout.removeAllViews()

        for(i in dots.indices){
            dots[i] = TextView(this)
            dots[i]!!.text = (Html.fromHtml("&#8226;"))
//            dots[i]!!.text = "b"
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(Color.BLUE)
            dotsLout.addView(dots[i])
        }
        if(dots.isNotEmpty()){
            dots[position]?.setTextColor(Color.BLACK)
        }

    }

}
