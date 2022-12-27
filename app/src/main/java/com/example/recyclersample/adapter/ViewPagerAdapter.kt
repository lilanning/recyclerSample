package com.example.recyclersample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(private val pagerDatas:List<PageDataBean>, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
       return pagerDatas.size
    }

//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val view = pagerDatas[position].getView()
//        container.addView(view)
//        return view!!
//    }

//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(pagerDatas[position].getView())
//    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pagerDatas[position].getTittle()
    }

    override fun getItem(position: Int): Fragment {
        return  pagerDatas[position].getView() as Fragment
    }
}