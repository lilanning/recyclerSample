package com.example.recyclersample

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.recyclersample.bean.Event
import com.example.recyclersample.databinding.ActivityMainBinding
import com.example.recyclersample.http.Data
import com.example.recyclersample.http.RetrofitManager
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    private val TAG = MainActivity::class.java.simpleName
    private lateinit var mainBinding: ActivityMainBinding


    var stepSimple: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        mainBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)

        setContentView(mainBinding.root)
        super.onCreate(savedInstanceState)


        mainBinding.stepSimple.setOnClickListener {
            startActivity(Intent(this, SimpleAdapterMainActivity::class.java))

        }


        mainBinding.stepTwo.setOnClickListener {
            val animation: ObjectAnimator =
                ObjectAnimator.ofFloat(mainBinding.stepTwo, "translationY", 0f, 0f)
            animation.duration = 1000
            animation.start()
            startActivity(Intent(this@MainActivity, SecondAdapterActivity::class.java))
        }

        mainBinding.viewPager.setOnClickListener {
            startActivity(Intent(this, ViewPageActivity::class.java))
            EventBus.getDefault().postSticky(Event(1, "这是一条粘性事件"))
        }

        mainBinding.stepViewPager2.setOnClickListener {
            startActivity(Intent(this, ViewPage2Activity::class.java))

        }

        mainBinding.stepNavi.setOnClickListener { view ->
            startActivity(Intent(this, NavigationActivity::class.java))
        }

//        val resId =
//            applicationContext.resources.getIdentifier("freesia", "drawable", applicationContext.packageName)
//        mainBinding.stepViewPager2.background = ResourcesCompat.getDrawable(applicationContext.resources,resId,null)


        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //未授权
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                100
            )
        } else {
            getContacts()
        }

        //Retrofit实现网络请求
        mainBinding.button.setOnClickListener {
            RetrofitManager.service.getHotWord("新歌榜", "json").enqueue(object :
                Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    val gson = Gson().fromJson<Data>(response.body()?.toString(), Data::class.java)
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Log.d(TAG, "onFailure: " + t.message)
                }

            })
//            RetrofitManager.service.getImage("动漫","json").enqueue(object :Callback<Image> {
//                override fun onResponse(call: Call<Image>, response: Response<Image>) {
//                    Log.d(TAG, "onResponse: " + response.body()?.imgurl)
//                    Glide.with(applicationContext).load(response.body()?.imgurl).into(mainBinding.wave)
//                }
//
//                override fun onFailure(call: Call<Image>, t: Throwable) {
//                    Log.d(TAG, "onFailure: " + t.message)
//                }
//
//            })
//            RetrofitManager.post("https://api.uomg.com/api/long2fh?dwzapi=urlcn&url=http://qrpay.uomg.com")
//            RetrofitManager.getUrl("https://api.uomg.com/api/get.kg?songurl=https://node.kg.qq.com/play?s=YaCv8EYfJunVWYcH")
//            RetrofitManager.getAsyncUrl("https://api.uomg.com/api/get.kg?songurl=https://node.kg.qq.com/play?s=YaCv8EYfJunVWYcH&format=json")
//            RetrofitManager.postAsync("https://api.uomg.com/api/long2fh?url=http://qrpay.uomg.com")

        }
    }

    private fun getContacts() {
        Toast.makeText(this, "getContacts", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && permissions[0] == android.Manifest.permission.READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts()
            } else {
                //未授权
                Toast.makeText(this, "file permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}





