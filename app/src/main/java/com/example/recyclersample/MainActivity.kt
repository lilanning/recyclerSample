package com.example.recyclersample

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.recyclersample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var mainBinding: ActivityMainBinding
    var stepSimple:Button?  = null
    override fun onCreate(savedInstanceState: Bundle?) {

        mainBinding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_main,null,false)

        setContentView(mainBinding.root)
        super.onCreate(savedInstanceState)


        mainBinding.stepSimple.setOnClickListener {
            startActivity(Intent(this,SimpleAdapterMainActivity::class.java))

        }


        mainBinding.stepTwo.setOnClickListener {
            val animation: ObjectAnimator = ObjectAnimator.ofFloat(mainBinding.stepTwo,"translationY",0f,0f)
            animation.duration = 1000
            animation.start()
            startActivity(Intent(this@MainActivity,SecondAdapterActivity::class.java))
        }

        mainBinding.viewPager.setOnClickListener {
            startActivity(Intent(this,ViewPageActivity::class.java))
        }



    }
}