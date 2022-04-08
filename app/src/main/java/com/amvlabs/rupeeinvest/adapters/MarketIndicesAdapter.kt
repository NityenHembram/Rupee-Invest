package com.amvlabs.rupeeinvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R
import kotlinx.android.synthetic.main.activity_welcome.view.*

class MarketIndicesAdapter():RecyclerView.Adapter<MarketIndicesAdapter.ViewHolder>() {

   var marketList = arrayListOf<String>("NIFTY 50","NIFTY BANK","SENSEX")
   var stockValue = arrayListOf<String>("17.784","37,752.05","59,447.18")
   var growth = arrayListOf<String>("+194.70 (\u200E+0.52%)","+194.70 (+0.52%)","+194.70 (\u200E+0.52%)")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketIndicesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_indices_itemview,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val marketName: TextView = itemView.findViewById<TextView>(R.id.marketTxt)
        val valueTxt:  TextView = itemView.findViewById<TextView>(R.id.shareValueTxt)
        val growthTxt :  TextView = itemView.findViewById<TextView>(R.id.shareGrowthTxt)
    }

    override fun onBindViewHolder(holder: MarketIndicesAdapter.ViewHolder, position: Int) {
        val a =  marketList[position]
        val b = stockValue[position]
        val c = growth[position]
//        holder.item.text = i

        holder.marketName.text = a
        holder.valueTxt.text = b
        holder.growthTxt.text = c
    }

    override fun getItemCount(): Int {
        return marketList.size
    }
}