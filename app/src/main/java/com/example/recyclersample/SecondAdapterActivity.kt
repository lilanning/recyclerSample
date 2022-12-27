package com.example.recyclersample

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclersample.adapter.OnItemClickListener
import com.example.recyclersample.adapter.RecyclerGridAdapter
import com.example.recyclersample.bean.SimpleBean
import com.example.recyclersample.databinding.ActivitySecondAdapterBinding
import com.example.recyclersample.resource.DataRes

class SecondAdapterActivity : AppCompatActivity() {

    private val TAG = "SecondAdapterActivity"

    private lateinit var secondAdapterBinding: ActivitySecondAdapterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondAdapterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.activity_second_adapter, null, false)
        setContentView(secondAdapterBinding.root)

        secondAdapterBinding.recycle.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.VERTICAL, false) as GridLayoutManager
        //循环添加数据
        for (item in 1..48) {
            val bean = SimpleBean()
            bean.setName("这是第${item}条数据，Item的标题内容，测试数据")
            bean.setResId(DataRes.instance.getRes()[item % DataRes.instance.getRes().size])
            bean.setImageHeight(DataRes.instance.getImgSize(item%12)[1])
            bean.setImageWidth(DataRes.instance.getImgSize(item%12)[0])
            DataRes.instance.addData(bean)
            Log.d(TAG, "onCreate: ${bean.getName()}  ")
        }

        //设置流布局
        secondAdapterBinding.recycle.layoutManager =
            StaggeredGridLayoutManager( 2, RecyclerView.VERTICAL,)
        //添加item间距
        secondAdapterBinding.recycle.addItemDecoration(ItemDecoration())

        val recyclerGridAdapter = RecyclerGridAdapter(this, DataRes.instance.getData())

        recyclerGridAdapter.bindClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@SecondAdapterActivity,
                    "点击第${position + 1}个item",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        secondAdapterBinding.recycle.adapter = recyclerGridAdapter


    }

    class ItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {

            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(10, 10, 10, 10)
        }
    }
}