package com.amvlabs.rupeeinvest.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.databinding.ActivityChartBinding
import com.amvlabs.rupeeinvest.model.GenerateRandomNumber
import org.eazegraph.lib.charts.BarChart
import org.eazegraph.lib.models.BarModel

class ChartActivity : AppCompatActivity() {

    lateinit var barChart:BarChart
    lateinit var bind:ActivityChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityChartBinding.inflate(layoutInflater)
        setContentView(bind.root)

        barChart = findViewById(R.id.barChart)
        val generate =GenerateRandomNumber()

        val numList = generate.randomNumGenerator()

        barChart.addBar(BarModel(numList[0], Color.DKGRAY))
        barChart.addBar(BarModel(numList[1], Color.DKGRAY))
        barChart.addBar(BarModel(numList[2], Color.DKGRAY))
        barChart.addBar(BarModel(numList[4], Color.DKGRAY))
        barChart.addBar(BarModel(numList[5], Color.DKGRAY))
        barChart.addBar(BarModel(numList[3], Color.DKGRAY))
        barChart.addBar(BarModel(numList[6], Color.DKGRAY))
        barChart.addBar(BarModel(numList[7], Color.DKGRAY))
        barChart.addBar(BarModel(numList[8], Color.DKGRAY))
        barChart.addBar(BarModel(numList[9], Color.DKGRAY))
        barChart.startAnimation()

    }
}