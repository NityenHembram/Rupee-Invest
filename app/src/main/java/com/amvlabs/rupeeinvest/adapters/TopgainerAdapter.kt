package com.amvlabs.rupeeinvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R

class TopgainerAdapter(): RecyclerView.Adapter<TopgainerAdapter.ViewHolder>() {

    private var companyNameList = arrayListOf<String>("Adani Green Energy","Grasim Industries","SBI Life Insurance","ITC","JSW Steel")
    private var stockValue = arrayListOf<String>("2,321.85","1,771.25","1,160.45","267.80","59.447.18")
    private var growth = arrayListOf<String>("+155.85 (\u200E+7.20%)","+99.30 (+5.31%)","+51.60 (\u200E+4.65%)","+11.15 (\u200E+4.34%)","+412.23 (\u200E+0.70%)")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopgainerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val companyName: TextView = itemView.findViewById<TextView>(R.id.company_name)
        val value: TextView = itemView.findViewById<TextView>(R.id.company_shareValue)
        val growth: TextView = itemView.findViewById<TextView>(R.id.company_growthValue)
    }

    override fun onBindViewHolder(holder: TopgainerAdapter.ViewHolder, position: Int) {
        val n = companyNameList[position]
        val v = stockValue[position]
        val g = growth[position]
        holder.companyName.text = n
        holder.value.text = v
        holder.growth.text = g
    }

    override fun getItemCount(): Int {
        return companyNameList.size
    }
}