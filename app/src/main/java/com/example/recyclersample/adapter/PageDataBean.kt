package com.example.recyclersample.adapter

import android.view.View

class PageDataBean() {

    private var view:View? = null

    private var tittle:String? = null


    fun getView(): View? {
        return view
    }
    fun getTittle(): String? {
        return tittle
    }

    fun setView(view1: View){
        view = view1
    }
    fun setTittle(text:String){
        tittle = text
    }

}