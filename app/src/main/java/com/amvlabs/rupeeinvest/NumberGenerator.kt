package com.amvlabs.rupeeinvest

import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.nextInt

class NumberGenerator {


    fun randomNumGenerator():ArrayList<Float>{
        val rand = Random
        val range  =IntRange(0,10)
        val list = arrayListOf<Float>()
        val timer =  Timer();
        timer.schedule(object : TimerTask(){
            override fun run() {
                for(i in 0..10){
                    val num = rand.nextInt(range)
                    list.add(num.toFloat())
                }
            }

        },0,1000)
       return list
    }

}