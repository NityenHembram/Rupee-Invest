package com.amvlabs.rupeeinvest.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.NumberGenerator
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.dialogs.RequestLogInDialog
import com.amvlabs.rupeeinvest.ui.ChartActivity
import com.amvlabs.rupeeinvest.ui.LoginActivity
import com.amvlabs.rupeeinvest.ui.isSignIn
import kotlinx.android.synthetic.main.activity_welcome.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


class MarketIndicesAdapter(private val context: Context, private val flag1:Boolean):RecyclerView.Adapter<MarketIndicesAdapter.ViewHolder>() {

   var marketList = arrayListOf<String>("NIFTY 50","NIFTY BANK","SENSEX")
   var stockValue = arrayListOf<String>("17.784","37,752.05","59,447.18")
   var growth = arrayListOf<String>("+194.70 (\u200E+0.52%)","+194.70 (+0.52%)","+194.70 (\u200E+0.52%)")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketIndicesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_indices_itemview,parent,false)
        return ViewHolder(context,view,flag1)
    }

    class ViewHolder(var context: Context,var itemView:View, private val f:Boolean):RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val marketName: TextView = itemView.findViewById<TextView>(R.id.marketTxt)
        val valueTxt:  TextView = itemView.findViewById<TextView>(R.id.shareValueTxt)
        val growthTxt :  TextView = itemView.findViewById<TextView>(R.id.shareGrowthTxt)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            if(!f){
                val dialog = RequestLogInDialog()
                dialog.showDialog(context)
            }else{
                context.startActivity(Intent(context,ChartActivity::class.java))
            }

        }


    }

    override fun onBindViewHolder(holder: MarketIndicesAdapter.ViewHolder, position: Int) {
        val a =  marketList[position]
        val b = stockValue[position]

//        holder.item.text = i
        val rand = Random
        val range  =IntRange(0,10)
        val list = arrayListOf<Float>()
        val timer =  Timer();


            timer.schedule(object : TimerTask(){
                override fun run() {
                    for(i in 0..10){
                        Log.d("TAG", "run: ")
                        val num = rand.nextInt(range)
                        list.add(num.toFloat())
                    }
                    GlobalScope.launch(Dispatchers.Default) {
                        withContext(Dispatchers.Main){
                            Log.d("TAG", "run:$list ")
                            val c = list[5]
                            holder.growthTxt.text = c.toString()
                            list.clear()
                        }

                    }


                }

            },0,1000)





        holder.marketName.text = a
        holder.valueTxt.text = b

    }

    override fun getItemCount(): Int {
        return marketList.size
    }
}