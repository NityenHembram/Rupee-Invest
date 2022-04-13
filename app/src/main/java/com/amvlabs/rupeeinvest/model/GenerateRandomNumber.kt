package com.amvlabs.rupeeinvest.model

import androidx.annotation.FloatRange
import kotlin.random.Random
import kotlin.random.nextInt

class GenerateRandomNumber {
    fun randomNumGenerator():ArrayList<Float>{
        val rand = Random
        val range  = IntRange(1,10)
        val list = arrayListOf<Float>()

        for(i in 0..10){
            val num = rand.nextInt(range)
            list.add(num.toFloat())
        }
        return list
    }
}