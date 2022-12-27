package com.example.recyclersample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.adapter.OnItemClickListener
import com.example.recyclersample.adapter.SimpleAdapter
import com.example.recyclersample.bean.SimpleBean
import com.example.recyclersample.databinding.ActivitySimpleBinding
import com.example.recyclersample.resource.DataRes


class SimpleAdapterMainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySimpleBinding
    private val TAG = "MainActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_simple, null, false)
        setContentView(mBinding.root)
        super.onCreate(savedInstanceState)


        for (item in 1..48){
            val bean = SimpleBean()
            bean.setName("这是第${item}条数据，Item的标题内容，测试数据")
            bean.setResId( DataRes.instance.getRes()[item% DataRes.instance.getRes().size])
            DataRes.instance.addData(bean)
            Log.d(TAG, "onCreate: ${bean.getName()}  ")
        }
        val simpleAdapter = SimpleAdapter(this,  DataRes.instance.getData() )
        simpleAdapter.bindClickListener(object :OnItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@SimpleAdapterMainActivity,"点击第${position+1}个item",Toast.LENGTH_SHORT).show()
            }
        })
        mBinding.recycle.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        mBinding.recycle.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ))
        mBinding.recycle.adapter = simpleAdapter
    }
}