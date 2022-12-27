package com.example.recyclersample.fragment

import com.example.recyclersample.R
import com.example.recyclersample.base.BaseFragment
import com.example.recyclersample.databinding.FragmentFaxianBinding

class FaXianFragment:BaseFragment<FragmentFaxianBinding>() {
    companion object{
        fun newInstance() = FaXianFragment()
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_faxian
    }
}