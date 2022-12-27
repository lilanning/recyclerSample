package com.example.recyclersample.fragment

import com.example.recyclersample.R
import com.example.recyclersample.base.BaseFragment
import com.example.recyclersample.databinding.FragmentTongxunluBinding
import com.example.recyclersample.databinding.FragmentWeixinBinding

class TongXunLuFragment:BaseFragment<FragmentTongxunluBinding>() {
    companion object{
        fun newInstance() = TongXunLuFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tongxunlu
    }
}