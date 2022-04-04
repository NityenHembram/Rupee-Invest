package com.amvlabs.rupeeinvest.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.model.ViewPagerItem

class ViewPagerAdapter(var itemList:ArrayList<ViewPagerItem>):RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.slid_title)
        val image = itemView.findViewById<ImageView>(R.id.slid_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.slide_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewHolder, position: Int) {
        val i = itemList[position]
        holder.name.text = i.title
        holder.image.setImageDrawable(i.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

