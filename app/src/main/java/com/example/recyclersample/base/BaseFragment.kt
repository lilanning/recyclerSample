package com.example.recyclersample.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T:ViewDataBinding>():Fragment() {

    private val TAG = "BaseFragment"

    lateinit var dataBinding: T
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView: ${this::class.java.simpleName}")
        dataBinding = DataBindingUtil.inflate(layoutInflater,getLayoutId(),null,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = activity
    }
    open fun isBindingInit(): Boolean {
        return this::dataBinding.isInitialized
    }

    abstract fun getLayoutId():Int
}