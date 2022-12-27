package com.example.recyclersample.bean

import androidx.fragment.app.Fragment

class PageDataBean() {

    private var fragment:Fragment? = null

    private var tittle:String? = null


    fun getView(): Fragment? {
        return fragment
    }
    fun getTittle(): String? {
        return tittle
    }

    fun setFragment(fragment: Fragment){
        this.fragment = fragment
    }
    fun setTittle(text:String){
        tittle = text
    }

}