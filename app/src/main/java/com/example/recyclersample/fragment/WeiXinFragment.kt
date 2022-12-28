package com.example.recyclersample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.recyclersample.R
import com.example.recyclersample.base.BaseFragment
import com.example.recyclersample.databinding.FragmentWeixinBinding
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URI

class WeiXinFragment: BaseFragment<FragmentWeixinBinding>() {

    companion object{
        fun newInstance() = WeiXinFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_weixin
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //setImageSrc(dataBinding.weiXinIv,"https://www.qqkw.com/d/file/p/2018/07-31/a58bf1118b9fec45a40c4a560f167ef6.jpg")
        //Glide.with(requireActivity()).load("https://www.qqkw.com/d/file/p/2018/07-31/a58bf1118b9fec45a40c4a560f167ef6.jpg").into(dataBinding.weiXinIv)
    }


}
@BindingAdapter(value = ["imageUrl"])
fun setImageSrc(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}

