package com.amvlabs.rupeeinvest.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.adapters.MarketIndicesAdapter
import com.amvlabs.rupeeinvest.adapters.StockAdapter
import com.amvlabs.rupeeinvest.adapters.TopLoserAdapter
import com.amvlabs.rupeeinvest.adapters.TopgainerAdapter

class HomeFragment : Fragment() {

    private lateinit var marketRecyclerView: RecyclerView
    private lateinit var topGainerRecyclerView: RecyclerView
    private lateinit var stockNewsRecyclerView: RecyclerView
    private lateinit var loserRecyclerView: RecyclerView
    private lateinit var marketAdapter:MarketIndicesAdapter






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        marketRecyclerView = v.findViewById(R.id.marketRecyclerView)
        topGainerRecyclerView = v.findViewById(R.id.topGainerRecyclerView)
        stockNewsRecyclerView = v.findViewById(R.id.stockNewsRecyclerView)
        loserRecyclerView = v.findViewById(R.id.loserRecyclerView)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arr = arrayListOf<String>("1","2","3","4","5","6")

        recyclerViewContainer(arr,view,marketRecyclerView)
        recyclerViewContainer(arr,view,topGainerRecyclerView)
        recyclerViewContainer(arr,view,stockNewsRecyclerView)
        recyclerViewContainer(arr,view,loserRecyclerView)

    }

    private fun recyclerViewContainer(arr:ArrayList<String>, view: View, recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = MarketIndicesAdapter(arr)
        recyclerView.adapter = StockAdapter(arr)
    }




}