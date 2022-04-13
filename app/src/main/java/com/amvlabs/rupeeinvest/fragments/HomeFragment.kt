package com.amvlabs.rupeeinvest.fragments

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.rupeeinvest.R
import com.amvlabs.rupeeinvest.adapters.*
import com.amvlabs.rupeeinvest.decoration.PageIndicatorDecoration
import com.amvlabs.rupeeinvest.ui.isSignIn

class HomeFragment : Fragment() {

    private lateinit var marketRecyclerView: RecyclerView
    private lateinit var topGainerRecyclerView: RecyclerView
    private lateinit var stockNewsRecyclerView: RecyclerView
    private lateinit var loserRecyclerView: RecyclerView
    private lateinit var marketAdapter:MarketIndicesAdapter

    lateinit var bannerRecyclerView: RecyclerView

    private lateinit var dotsLout:LinearLayout
    private lateinit var dots:Array<TextView?>

    private var isSignInFlag:Boolean =false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSignInFlag = requireActivity().intent.getBooleanExtra(isSignIn,false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        marketRecyclerView = v.findViewById(R.id.marketRecyclerView)
        topGainerRecyclerView = v.findViewById(R.id.topGainerRecyclerView)
        stockNewsRecyclerView = v.findViewById(R.id.stockNewsRecyclerView)
        loserRecyclerView = v.findViewById(R.id.loserRecyclerView)
        bannerRecyclerView = v.findViewById(R.id.banner_recyclerView)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        marketRecyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        marketRecyclerView.adapter = MarketIndicesAdapter(requireActivity(),isSignInFlag)

        stockNewsRecyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        stockNewsRecyclerView.adapter = StockAdapter(requireActivity(),isSignInFlag)

        topGainerRecyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        topGainerRecyclerView.adapter = TopgainerAdapter(requireActivity(),isSignInFlag)

        loserRecyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        loserRecyclerView.adapter = TopLoserAdapter(requireActivity(),isSignInFlag)

        bannerRecyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        bannerRecyclerView.adapter = BannerAdapter()


        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(bannerRecyclerView)
        bannerRecyclerView.addItemDecoration(PageIndicatorDecoration())




    }






}