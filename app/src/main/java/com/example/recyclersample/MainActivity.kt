package com.example.recyclersample

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.recyclersample.bean.Event
import org.greenrobot.eventbus.EventBus
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
            EventBus.getDefault().postSticky(Event(1,"这是一条粘性事件"))
        }

        mainBinding.stepViewPager2.setOnClickListener {
            startActivity(Intent(this,ViewPage2Activity::class.java))

        }

        mainBinding.stepNavi.setOnClickListener { view->
            startActivity(Intent(this,NavigationActivity::class.java))
        }

//        val resId =
//            applicationContext.resources.getIdentifier("freesia", "drawable", applicationContext.packageName)
//        mainBinding.stepViewPager2.background = ResourcesCompat.getDrawable(applicationContext.resources,resId,null)


    }
}