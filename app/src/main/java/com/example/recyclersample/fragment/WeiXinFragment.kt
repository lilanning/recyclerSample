package com.example.recyclersample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recyclersample.R
import com.example.recyclersample.base.BaseFragment
import com.example.recyclersample.databinding.FragmentWeixinBinding

class WeiXinFragment: BaseFragment<FragmentWeixinBinding>() {

    companion object{
        fun newInstance() = WeiXinFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_weixin
    }


}