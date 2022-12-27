package com.example.recyclersample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.recyclersample.bean.PageDataBean
import com.example.recyclersample.adapter.ViewPagerAdapter
import com.example.recyclersample.databinding.ActivityViewPageBinding
import com.example.recyclersample.fragment.FaXianFragment
import com.example.recyclersample.fragment.MyFragment
import com.example.recyclersample.fragment.TongXunLuFragment
import com.example.recyclersample.fragment.WeiXinFragment
import com.google.android.material.tabs.TabLayout

class ViewPageActivity : AppCompatActivity() {
    private val TAG = "ViewPageActivity"

    private val pagerData: List<PageDataBean> = ArrayList()
    private val res = arrayListOf<Int>(
        R.drawable.tulip,
        R.drawable.sunflower,
        R.drawable.rose,
        R.drawable.poppy
    )
    private lateinit var viewPageBinding: ActivityViewPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPageBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.activity_view_page, null, false)
        setContentView(viewPageBinding.root)
        initView()
        val viewPagerAdapter = ViewPagerAdapter(pagerData,supportFragmentManager)
        viewPageBinding.viewPager.also {
            it.adapter = viewPagerAdapter
        }
        (viewPageBinding.tablayout as TabLayout).setupWithViewPager(viewPageBinding.viewPager)
        viewPageBinding.viewPager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    //当当前页面被滚动时，该方法将被调用，可以作为程序启动的平滑滚动的一部分，也可以作为用户启动的触摸滚动的一部分。
                    // position -当前显示的第一页的位置索引。
                    // 如果positionOffset非零，页面位置+1将是可见的。
                    // positionOffset - from[0,1)的值，表示位置处与页面的偏移量。
                    // positionOffsetPixels -以像素为单位的值，指示位置的偏移量。
                }

                override fun onPageSelected(position: Int) {
                    //当选择新页面时，将调用此方法。动画不一定是完整的。position -新选择页面的位置索引。
                }

                override fun onPageScrollStateChanged(state: Int) {
                    //当滚动状态改变时调用。用于发现用户何时开始拖动，分页器何时自动设置到当前页面，或者何时完全停止/空闲。
                    // Params: state—新的滚动状态。参见:SCROLL_STATE_IDLE, scroll_state_拖动，scroll_state_setting
                }

            }
        )

    }


    private fun initView() {
        Log.d(TAG, "initView: ")
//        for (i in 0 until res.size) {
//            val viewBinding =
//                DataBindingUtil.inflate<PagerBinding>(layoutInflater, R.layout.pager, null, false)
//            viewBinding.pagerText.text = "页面$i"
//            viewBinding.pageImage.setBackgroundResource(res[i])
//            val dataBean = PageDataBean()
//            dataBean.setTittle("页面$i")
//            dataBean.setView(viewBinding.root)
//            (pagerData as ArrayList<PageDataBean>).add(dataBean)
//        }

        val dataBean = PageDataBean()
        dataBean.setFragment(WeiXinFragment.newInstance())
        dataBean.setTittle("微信")
        val dataBean1 = PageDataBean()
        dataBean1.setFragment(TongXunLuFragment.newInstance())
        dataBean1.setTittle("通讯录")
        val dataBean2 = PageDataBean()
        dataBean2.setFragment(FaXianFragment.newInstance())
        dataBean2.setTittle("发现")
        val dataBean3 = PageDataBean()
        dataBean3.setFragment(MyFragment.newInstance())
        dataBean3.setTittle("我的")
        (pagerData as ArrayList<PageDataBean>).add(dataBean)
        (pagerData as ArrayList<PageDataBean>).add(dataBean1)
        (pagerData as ArrayList<PageDataBean>).add(dataBean2)
        (pagerData as ArrayList<PageDataBean>).add(dataBean3)

    }
}