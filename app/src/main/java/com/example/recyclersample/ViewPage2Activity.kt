package com.example.recyclersample

import android.R.id.tabs
import android.content.res.ColorStateList
import android.graphics.Color.red
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclersample.adapter.ViewPagerViewAdapter
import com.example.recyclersample.bean.ViewPagerBean
import com.example.recyclersample.databinding.ActivityViewPage2Binding
import com.example.recyclersample.fragment.FaXianFragment
import com.example.recyclersample.fragment.MyFragment
import com.example.recyclersample.fragment.TongXunLuFragment
import com.example.recyclersample.fragment.WeiXinFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPage2Activity : AppCompatActivity() {
    private var TAG = "ViewPage2Activity"

    private lateinit var viewPage2Binding: ActivityViewPage2Binding

    private var list: List<ViewPagerBean> = arrayListOf(
        ViewPagerBean(WeiXinFragment.newInstance(), "微信"),
        ViewPagerBean(TongXunLuFragment.newInstance(), "通讯录"),
        ViewPagerBean(FaXianFragment.newInstance(), "发现"),
        ViewPagerBean(MyFragment.newInstance(), "我的")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPage2Binding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_view_page2,null,false)
        initViewPager2()
        setContentView(viewPage2Binding.root)

    }

    private fun initViewPager2(){
        viewPage2Binding.viewpager2.adapter = ViewPagerViewAdapter(list as ArrayList<ViewPagerBean>,this)
        viewPage2Binding.viewpager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        TabLayoutMediator(viewPage2Binding.tablayout,viewPage2Binding.viewpager2
        ) { tab, position ->
            val tabView: TextView = TextView(this@ViewPage2Activity)
            tabView.text = list[position].tittle
            //tabView.background = ResourcesCompat.getDrawable(application.resources, R.color.colorAccent, null)
            tabView.textSize = 20f
            tab.customView = tabView
        }.attach()

        viewPage2Binding.viewpager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                val tabCount = viewPage2Binding.tablayout.tabCount
                Log.d(TAG, "onPageSelected: $tabCount")
                for (i in 0 until  tabCount){

                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })


    }
}