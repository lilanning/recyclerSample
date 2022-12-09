package com.example.recyclersample.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(private val pagerDatas:List<PageDataBean>) : PagerAdapter() {
    override fun getCount(): Int {
       return pagerDatas.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = pagerDatas[position].getView()
        container.addView(view)
        return view!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(pagerDatas[position].getView())
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pagerDatas[position].getTittle()
    }
}