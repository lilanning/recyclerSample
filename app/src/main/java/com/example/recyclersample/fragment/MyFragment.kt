package com.example.recyclersample.fragment

import com.example.recyclersample.R
import com.example.recyclersample.base.BaseFragment
import com.example.recyclersample.databinding.FragmentMyBinding

class MyFragment:BaseFragment<FragmentMyBinding>() {

    companion object{
        fun newInstance() = MyFragment()
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }
}