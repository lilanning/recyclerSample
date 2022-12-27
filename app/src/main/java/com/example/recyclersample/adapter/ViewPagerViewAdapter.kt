package com.example.recyclersample.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.recyclersample.bean.ViewPagerBean

class ViewPagerViewAdapter(
    private val list: ArrayList<ViewPagerBean>,
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return list.size
    }


    override fun createFragment(position: Int): Fragment {
        return list[position].fragment
    }


}