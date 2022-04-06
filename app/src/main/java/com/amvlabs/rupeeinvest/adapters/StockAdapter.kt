package com.amvlabs.rupeeinvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R

class StockAdapter(var list:ArrayList<String>): RecyclerView.Adapter<StockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val item: TextView = itemView.findViewById<TextView>(R.id.myText)
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        val i = list[position]
        holder.item.text = i
    }

    override fun getItemCount(): Int {
        return list.size
    }
}