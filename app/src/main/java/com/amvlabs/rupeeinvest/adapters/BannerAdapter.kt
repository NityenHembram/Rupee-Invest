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

class BannerAdapter():RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    val images = listOf<Int>(R.drawable.stock1,R.drawable.stock2,R.drawable.stock3,R.drawable.stock4)
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val bannerImg = itemView.findViewById<ImageView>(R.id.bannerImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.banner_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BannerAdapter.ViewHolder, position: Int) {
        val img = images[position]
        holder.bannerImg.setImageResource(img)
    }

    override fun getItemCount(): Int {
        return images.size
    }


}