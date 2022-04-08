package com.amvlabs.rupeeinvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R

class TopLoserAdapter(): RecyclerView.Adapter<TopLoserAdapter.ViewHolder>() {

    private var companyNameList = arrayListOf<String>("Cipla","Colgate-Palmolive(India)","Tech Mahindra","DLF","ICICI Lambard")
    private var stockValue = arrayListOf<String>("1,011.60","1,546.65","1.448.75","369.10","1,380.20")
    private var growth = arrayListOf<String>("-25.10 (\u200E-2.42%)","-22.30 (-1.41%)","+19.80 (\u200E+1.35%)","-5.15 (\u200E+1.28%)","+16.45 (\u200E+1.18%)")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopLoserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val companyName: TextView = itemView.findViewById<TextView>(R.id.company_name)
        val value: TextView = itemView.findViewById<TextView>(R.id.company_shareValue)
        val growth: TextView = itemView.findViewById<TextView>(R.id.company_growthValue)
    }

    override fun onBindViewHolder(holder: TopLoserAdapter.ViewHolder, position: Int) {
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