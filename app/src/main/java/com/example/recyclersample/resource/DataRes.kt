package com.example.recyclersample.resource

import com.example.recyclersample.R
import com.example.recyclersample.adapter.SimpleBean

class DataRes(){


    companion object {

        val instance: DataRes by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataRes()
        }
    }

    private val datas: List<SimpleBean> = ArrayList()
    private val res = arrayOf<Int>(
        R.drawable.daffodil,
        R.drawable.dahlia,
        R.drawable.daisy,
        R.drawable.freesia,
        R.drawable.lilac,
        R.drawable.lily,
        R.drawable.marigold,
        R.drawable.peony,
        R.drawable.poppy,
        R.drawable.rose,
        R.drawable.sunflower,
        R.drawable.tulip
    )

    private val imageSizes = arrayOf(
        intArrayOf(432, 466),
        intArrayOf(462, 478),
        intArrayOf(712, 678),
        intArrayOf(764, 686),
        intArrayOf(746, 528),
        intArrayOf(970, 1037),
        intArrayOf(576, 720),
        intArrayOf(792, 676),
        intArrayOf(650, 686),
        intArrayOf(782, 676),
        intArrayOf(794, 714),
        intArrayOf(572, 676),
    )

    fun getRes() = res

    fun addData(data:SimpleBean?){
        if (data != null) {
            (datas as ArrayList<SimpleBean>).add(data)
        }
    }


    fun  getData(): List<SimpleBean> {
        return datas
    }

    fun getImgSize( int: Int): IntArray {
        return imageSizes[int]
    }



}

