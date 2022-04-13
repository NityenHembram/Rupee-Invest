package com.amvlabs.rupeeinvest.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.dialogs.RequestLogInDialog
import com.amvlabs.rupeeinvest.ui.ChartActivity

class StockAdapter(private val context: Context, private val flag1:Boolean): RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    private var companyNameList = arrayListOf<String>("Infosys","IDFC First Bank","Lemon Tree Hotels","Force Motors","Power Grid Crop")
    private var stockValue = arrayListOf<String>("1,814.60","41.90","66.70","1,156.80","232.60")
    private var growth = arrayListOf<String>("+3.60 (\u200E+0.20%)","-0.55 (-1.30%)","+1.15 (\u200E+1.75%)","+7.70 (\u200E+0.67%)","+1.25 (\u200E+0.54%)")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view,flag1,context)
    }

    class ViewHolder(itemView: View, private val f:Boolean,var context: Context): RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val companyName: TextView = itemView.findViewById<TextView>(R.id.company_name)
        val value: TextView = itemView.findViewById<TextView>(R.id.company_shareValue)
        val growth: TextView = itemView.findViewById<TextView>(R.id.company_growthValue)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            if(!f){
                val dialog = RequestLogInDialog()
                dialog.showDialog(context)
            }else{
                context.startActivity(Intent(context, ChartActivity::class.java))
            }

        }
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
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